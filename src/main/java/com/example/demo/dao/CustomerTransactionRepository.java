package com.example.demo.dao;

import com.example.demo.model.db.CustomerTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerTransactionRepository extends CrudRepository<CustomerTransaction, Integer> {
    @Query(value = "select ct from CustomerTransaction ct where ct.customerId = :customerId and FUNCTION('YEAR', date) = :year and FUNCTION('MONTH', date) = :month order by date")
    List<CustomerTransaction> findAllByCustomerIdAndYearAndMonth(@Param("customerId") Integer customerId, @Param("year") Integer year, @Param("month") Integer month);
}
