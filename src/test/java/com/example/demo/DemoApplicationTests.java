package com.example.demo;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.demo.controller.SearchController;
import com.example.demo.dao.CustomerInfoRepository;
import com.example.demo.model.db.CustomerInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private SearchController searchController;

    @Autowired
    CustomerInfoRepository customerInfoRepository;

    @Autowired
    EntityManager em;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    public void canSeeSearchPage() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void searchCustomerInfoWithEm(){
        StoredProcedureQuery storedProcedureQuery = em.createNamedStoredProcedureQuery(CustomerInfo.GET_CUSTOMER_INFO);
        storedProcedureQuery.setParameter("fcustomer_id", 1);
        storedProcedureQuery.setParameter("fyear", 2016);
        storedProcedureQuery.setParameter("fmonth", 6);
        List resultList = storedProcedureQuery.getResultList();
        Assert.assertFalse(resultList.isEmpty());
    }


    @Test
    public void searchCustomerInfoWithRepository(){
        CustomerInfo customerInfoBy = customerInfoRepository.findCustomerInfoByIdAndYearAndMonth(1, 2016, 6);
        Assert.assertNotNull(customerInfoBy);
    }

    @Test
	public void canLoadCustomerInfo() {
        Assert.fail("Not implemented");
    }

}
