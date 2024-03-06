package com.nexusforge.springgraphqlplayground.lec06.controller;

import com.nexusforge.springgraphqlplayground.lec06.entity.Customer;
import com.nexusforge.springgraphqlplayground.lec06.entity.CustomerOrder;
import com.nexusforge.springgraphqlplayground.lec06.entity.CustomerWithOrder;
import com.nexusforge.springgraphqlplayground.lec06.service.CustomerOrderDataFetcher;
import com.nexusforge.springgraphqlplayground.lec06.service.CustomerService;
import com.nexusforge.springgraphqlplayground.lec06.service.OrderService;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerOrderDataFetcher dataFetcher;
    @QueryMapping("customers")
    public Flux<CustomerWithOrder> customers(DataFetchingFieldSelectionSet selectionSet) {

        return this.dataFetcher.customerOrders(selectionSet);
    }

 /*   @SchemaMapping(typeName = "Customer")
    public Flux<CustomerOrder> orders(Customer customer){
        return this.orderService.ordersByCustomers(customer.getName());
    }*/

}
