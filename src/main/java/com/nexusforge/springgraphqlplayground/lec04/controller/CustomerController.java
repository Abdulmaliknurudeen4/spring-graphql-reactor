package com.nexusforge.springgraphqlplayground.lec04.controller;

import com.nexusforge.springgraphqlplayground.lec04.entity.Customer;
import com.nexusforge.springgraphqlplayground.lec04.service.CustomerService;
import com.nexusforge.springgraphqlplayground.lec04.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

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


   /* @BatchMapping(typeName = "Customer")
    //N+1
    public Flux<List<CustomerOrder>> orders(List<Customer> list) {
        return this.orderService.ordersByCustomersNames(list.stream().map(Customer::getName).toList());
    }*/

    @BatchMapping(typeName = "Customer")
    //N+1
    public Mono<Map<Customer, List<CustomerOrder>>> orders(List<Customer> list) {
        return this.orderService.fetchOrderAsMap(list);
    }
}
