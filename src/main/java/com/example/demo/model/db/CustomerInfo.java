package com.example.demo.model.db;

import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = CustomerInfo.GET_CUSTOMER_INFO,
        procedureName = "get_customer_info",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "fcustomer_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "fyear", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "fmonth", type = Integer.class)
        },
        resultClasses = {CustomerInfo.class}
)
@Entity
public class CustomerInfo {
    public static final String GET_CUSTOMER_INFO = "get_customer_info";

    private Integer customerId;
    private Double balance;
    private Boolean fastSpender;
    private Boolean bigSpender;
    private Boolean bigTicketSpender;
    private Boolean potentialSaver;
    private String personType;

    @Id
    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name = "balance")
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Column(name = "fast_spender")
    public Boolean getFastSpender() {
        return fastSpender;
    }

    public void setFastSpender(Boolean fastSpender) {
        this.fastSpender = fastSpender;
    }

    @Column(name = "big_spender")
    public Boolean getBigSpender() {
        return bigSpender;
    }

    public void setBigSpender(Boolean bigSpender) {
        this.bigSpender = bigSpender;
    }

    @Column(name = "big_ticket_spender")
    public Boolean getBigTicketSpender() {
        return bigTicketSpender;
    }

    public void setBigTicketSpender(Boolean bigTicketSpender) {
        this.bigTicketSpender = bigTicketSpender;
    }

    @Column(name = "potential_saver")
    public Boolean getPotentialSaver() {
        return potentialSaver;
    }

    public void setPotentialSaver(Boolean potentialSaver) {
        this.potentialSaver = potentialSaver;
    }

    @Column(name = "person_type")
    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
}
