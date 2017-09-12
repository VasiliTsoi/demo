package com.example.demo.controller;

import com.example.demo.model.CustomerInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SearchController {
    @RequestMapping(value = {"/", "/search"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("customer", new CustomerInfo());
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@Valid @ModelAttribute(value = "customer") CustomerInfo customerInfo, BindingResult bindingResult, Model model) {
        return "search";
    }
}
