package com.nexusforge.springgraphqlplayground.lec05.controller;

import com.nexusforge.springgraphqlplayground.lec05.service.CustomerService;
import com.nexusforge.springgraphqlplayground.lec05.entity.Customer;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @QueryMapping("customers")
    public Flux<Customer> customers(DataFetchingEnvironment environment) {
        System.out.println("customer" + environment.getDocument());
        System.out.println(environment.getOperationDefinition());
        return this.service.allCustomer();
    }



}
