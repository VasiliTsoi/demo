package com.example.demo.service;

import com.example.demo.model.db.CustomerInfo;

public interface SearchCustomerInfoService {
    CustomerInfo findCustomerInfoByIdAndYearAndMonth(Integer customerId, Integer year, Integer month);
}
