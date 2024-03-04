package com.nexusforge.springgraphqlplayground.sec01.lec03.controller;

import com.nexusforge.springgraphqlplayground.sec01.lec03.entity.Customer;
import com.nexusforge.springgraphqlplayground.sec01.lec03.service.CustomerService;
import com.nexusforge.springgraphqlplayground.sec01.lec03.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;
    @Autowired
    private OrderService orderService;

    @QueryMapping("customers")
    public Flux<Customer> customers() {
        return this.service.allCustomer();
    }


    @SchemaMapping(typeName = "Customer")
    public Flux<CustomerOrder> orders(Customer customer){
        return this.orderService.ordersByCustomers(customer.getName());
    }

}
