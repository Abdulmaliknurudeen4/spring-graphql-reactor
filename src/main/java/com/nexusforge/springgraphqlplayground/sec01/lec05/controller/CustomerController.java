package com.nexusforge.springgraphqlplayground.sec01.lec05.controller;

import com.nexusforge.springgraphqlplayground.sec01.lec05.entity.Customer;
import com.nexusforge.springgraphqlplayground.sec01.lec05.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @QueryMapping("customers")
    public Flux<Customer> customers() {
        return this.service.allCustomer();
    }



}
