package com.example.demo.service;

import com.example.demo.dao.CustomerInfoRepository;
import com.example.demo.model.db.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchCustomerInfoServiceImpl implements SearchCustomerInfoService {
    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    public CustomerInfo findCustomerInfoByIdAndYearAndMonth(Integer customerId, Integer year, Integer month) {
        return customerInfoRepository.findCustomerInfoByIdAndYearAndMonth(customerId, year, month);
    }
}
