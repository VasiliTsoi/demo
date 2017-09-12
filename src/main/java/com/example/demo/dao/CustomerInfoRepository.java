package com.example.demo.dao;

import com.example.demo.model.db.CustomerInfo;

public interface CustomerInfoRepository {
    CustomerInfo findCustomerInfoByIdAndYearAndMonth(Integer customerId, Integer year, Integer month);
}
