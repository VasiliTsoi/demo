CREATE SCHEMA customers;

USE customers;

CREATE TABLE IF NOT EXISTS transactions (
  id          INT(11) NOT NULL AUTO_INCREMENT,
  customer_id INT(11) NOT NULL,
  date        TIMESTAMP,
  amount      DECIMAL(14, 2),
  description VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE OR REPLACE VIEW deposits_view AS
  SELECT
    customer_id,
    year(date)  AS year,
    month(date) AS month,
    day(date)   AS day,
    sum(amount) AS deposit
  FROM transactions
  WHERE amount > 0
  GROUP BY customer_id, year(date), month(date), day(date);

DROP FUNCTION IF EXISTS check_if_customer_fast_spender;
DELIMITER $$
CREATE FUNCTION check_if_customer_fast_spender(fcustomer_id INT(11), fdeposit DECIMAL, fyear INT, fmonth TINYINT,
                                               fday         TINYINT)
  RETURNS TINYINT(1)
  BEGIN
    DECLARE spent_in_7_days DECIMAL DEFAULT 0;
    SET spent_in_7_days = (SELECT abs(sum(amount))
                           FROM transactions
                           WHERE customer_id = fcustomer_id
                                 AND year(date) = fyear
                                 AND month(date) = fmonth
                                 AND day(date) BETWEEN fday AND (fday + 7)
                                 AND amount < 0
                           GROUP BY customer_id);
    RETURN CASE WHEN coalesce(spent_in_7_days, 0) > 0 AND spent_in_7_days / fdeposit >= 0.75
      THEN 1
           ELSE 0 END;
  END;
$$
DELIMITER ;

CREATE OR REPLACE VIEW monthly_transactions_view AS
  SELECT
    customer_id,
    year(date)              AS year,
    month(date)             AS month,
    DATE_FORMAT(date, '%p') AS am_pm,
    count(*)                AS count,
    abs(min(amount))        AS max_spent,
    abs(sum(amount))        AS total_spent
  FROM transactions
  WHERE amount < 0 -- exclude deposits
  GROUP BY customer_id, year(date), month(date), DATE_FORMAT(date, '%p');

CREATE OR REPLACE VIEW monthly_deposits_view AS
  SELECT
    customer_id,
    year(date)  AS year,
    month(date) AS month,
    sum(amount) AS deposit
  FROM transactions
  WHERE amount > 0
  GROUP BY customer_id, year(date), month(date);

DROP PROCEDURE IF EXISTS get_customer_info;
DELIMITER $$
CREATE PROCEDURE get_customer_info(IN fcustomer_id INT(11), IN fyear INT, IN fmonth TINYINT)
  BEGIN
    DECLARE fast_count INT;
    DECLARE am_count INT;
    DECLARE am_max DECIMAL;
    DECLARE am_total DECIMAL;
    DECLARE pm_count INT;
    DECLARE pm_max DECIMAL;
    DECLARE pm_total DECIMAL;
    DECLARE total_count INT;
    DECLARE montly_deposit DECIMAL;
    DECLARE balance DECIMAL;

    SELECT count(*)
    INTO fast_count
    FROM deposits_view
    WHERE customer_id = fcustomer_id
          AND check_if_customer_fast_spender(customer_id, deposit, year, month, day) = 1;


    SELECT
      mtv.count,
      mtv.max_spent,
      mtv.total_spent
    INTO am_count, am_max, am_total
    FROM monthly_transactions_view mtv
    WHERE customer_id = fcustomer_id
          AND mtv.year = fyear
          AND mtv.month = fmonth
          AND mtv.am_pm = 'AM';

    SELECT
      mtv.count,
      mtv.max_spent,
      mtv.total_spent
    INTO pm_count, pm_max, pm_total
    FROM monthly_transactions_view mtv
    WHERE mtv.customer_id = fcustomer_id
          AND mtv.year = fyear
          AND mtv.month = fmonth
          AND mtv.am_pm = 'PM';

    SET total_count = COALESCE(am_count, 0) + COALESCE(pm_count, 0);

    SELECT mtv.deposit
    INTO montly_deposit
    FROM monthly_deposits_view mtv
    WHERE mtv.customer_id = fcustomer_id
          AND mtv.year = fyear
          AND mtv.month = fmonth;
    SET montly_deposit = coalesce(montly_deposit, 0);

    SELECT sum(amount)
    INTO balance
    FROM transactions
    GROUP BY customer_id
    HAVING customer_id = fcustomer_id;

    SELECT
      fcustomer_id  AS customer_id,
      CASE WHEN fast_count > 0
        THEN 1
      ELSE 0 END AS fast_spender,
      CASE WHEN total_count > 0 AND am_count / total_count > 0.5
        THEN 'AM'
      WHEN total_count > 0 AND pm_count / total_count > 0.5
        THEN 'PM'
      ELSE 'NA' END AS person_type,
      CASE WHEN
        greatest(COALESCE(am_max, 0.0), COALESCE(pm_max, 0.0)) > 1000
        THEN 1
      ELSE 0 END AS big_ticket_spender,
      CASE WHEN montly_deposit > 0 AND (COALESCE(am_total, 0.0) + COALESCE(pm_total, 0.0)) / montly_deposit > 0.8
        THEN 1
      ELSE 0 END AS big_spender,
      CASE WHEN montly_deposit > 0 AND (COALESCE(am_total, 0.0) + COALESCE(pm_total, 0.0)) / montly_deposit < 0.25
        THEN 1
      ELSE 0 END AS potential_saver,
      balance
    FROM dual
    WHERE EXISTS(SELECT 1 FROM transactions where customer_id = fcustomer_id);

  END;
$$
DELIMITER ;
