package com.example.demo.model.form;

import com.example.demo.model.db.CustomerInfo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CustomerSearchForm {
    @NotNull
    @Min(value = 0)
    @Max(value = Integer.MAX_VALUE)
    private Integer id;

    @NotNull
    @Min(value = 1970)
    private Integer year;

    @NotNull
    @Min(value = 1)
    @Max(value = 12)
    private Integer month;

    private CustomerInfo customerInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
}
