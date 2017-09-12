package com.example.demo.controller;

import com.example.demo.model.form.CustomerSearchForm;
import com.example.demo.service.SearchCustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SearchController {
    @Autowired
    private SearchCustomerInfoService searchCustomerInfoService;

    @RequestMapping(value = {"/", "/search"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("customer", new CustomerSearchForm());
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@Valid @ModelAttribute(value = "customer") CustomerSearchForm customerSearchForm, BindingResult bindingResult, Model model) {
        searchCustomerInfoService.findCustomerInfoByIdAndYearAndMonth(customerSearchForm.getId(), 2016, 6);
        return "search";
    }
}
