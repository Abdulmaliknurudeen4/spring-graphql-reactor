package com.nexusforge.springgraphqlplayground.sec01.lec02.controller;

import com.nexusforge.springgraphqlplayground.sec01.lec02.entity.AgeRangeFilter;
import com.nexusforge.springgraphqlplayground.sec01.lec02.entity.Customer;
import com.nexusforge.springgraphqlplayground.sec01.lec02.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @QueryMapping("customers")
    public Flux<Customer> customers() {
        return this.service.allCustomer();
    }

    @QueryMapping("customerById")
    public Mono<Customer> customerById(@Argument("id") Integer id) {
        return this.service.customerById(id);
    }

    @QueryMapping("customersNameContains")
    public Flux<Customer> customersNameContains(@Argument("name") String name) {
        return this.service.nameContains(name);
    }

    @QueryMapping("customersByAgeRange")
    public Flux<Customer> customersByAgeRange(@Argument("filter") AgeRangeFilter filter) {
        return this.service.filterByAge(filter);
    }


}
