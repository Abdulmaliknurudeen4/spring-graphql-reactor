package com.nexusforge.springgraphqlplayground.lec06.controller;

import com.nexusforge.springgraphqlplayground.lec06.entity.CustomerWithOrder;
import com.nexusforge.springgraphqlplayground.lec06.service.CustomerService;
import com.nexusforge.springgraphqlplayground.lec06.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CustomerRestController {
    @Autowired
    private CustomerService service;
    @Autowired
    private OrderService orderService;

    @GetMapping("customer")
    public Flux<CustomerWithOrder> customerOrders() {
        return this.service.allCustomer()
                .flatMap(c ->
                        this.orderService.ordersByCustomers(c.getName())
                                .collectList()
                                .map(l -> CustomerWithOrder.create(c.getId(), c.getName(), c.getAge(), c.getCity(), l))
                );

    }




}

