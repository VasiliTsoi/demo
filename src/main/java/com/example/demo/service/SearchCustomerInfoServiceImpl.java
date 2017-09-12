package com.example.demo.service;

import com.example.demo.dao.CustomerInfoRepository;
import com.example.demo.dao.CustomerTransactionRepository;
import com.example.demo.model.db.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchCustomerInfoServiceImpl implements SearchCustomerInfoService {
    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    public CustomerInfo findCustomerInfoByIdAndYearAndMonth(Integer customerId, Integer year, Integer month) {
        CustomerInfo customerInfo = customerInfoRepository.findCustomerInfoByIdAndYearAndMonth(customerId, year, month);
        if (customerInfo != null) {
            customerInfo.setTransactions(customerTransactionRepository.findAllByCustomerIdAndYearAndMonth(customerId, year, month));
        }
        return customerInfo;
    }
}
