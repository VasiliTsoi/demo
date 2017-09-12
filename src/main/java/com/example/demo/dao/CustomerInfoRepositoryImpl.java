package com.example.demo.dao;

import com.example.demo.model.db.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class CustomerInfoRepositoryImpl implements CustomerInfoRepository {
    @Autowired
    private EntityManager em;

    public CustomerInfo findCustomerInfoByIdAndYearAndMonth(Integer customerId, Integer year, Integer month) {
        StoredProcedureQuery storedProcedureQuery = em.createNamedStoredProcedureQuery(CustomerInfo.GET_CUSTOMER_INFO);
        storedProcedureQuery.setParameter("fcustomer_id", customerId);
        storedProcedureQuery.setParameter("fyear", year);
        storedProcedureQuery.setParameter("fmonth", month);
        storedProcedureQuery.setMaxResults(1);
        List resultList = storedProcedureQuery.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return (CustomerInfo) resultList.get(0);
    }
}
