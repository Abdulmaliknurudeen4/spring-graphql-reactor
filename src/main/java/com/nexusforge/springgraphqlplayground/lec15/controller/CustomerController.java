package com.nexusforge.springgraphqlplayground.lec15.controller;

import com.nexusforge.springgraphqlplayground.lec15.dto.CustomerDto;
import com.nexusforge.springgraphqlplayground.lec15.dto.CustomerNotFound;
import com.nexusforge.springgraphqlplayground.lec15.dto.DeleteResponseDto;
import com.nexusforge.springgraphqlplayground.lec15.exceptions.ApplicationErrors;
import com.nexusforge.springgraphqlplayground.lec15.service.CustomerService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService service;

    @QueryMapping
    public Flux<CustomerDto> customers(DataFetchingEnvironment environment) {
        var callerId = environment.getGraphQlContext().get("caller-id");
        System.out.println("CALLER ID " + callerId);
        return this.service.allCustomer();
    }

    @QueryMapping
    public Mono<Object> customerById(@Argument Integer id) {
        return this.service.customerById(id)
                .cast(Object.class)
                .switchIfEmpty(Mono.just(CustomerNotFound.create(id)));
        // .handle(isSuperUser());

    }

    @MutationMapping
    public Mono<CustomerDto> createCustomer(@Argument CustomerDto customer) {
        return Mono.just(customer)
                .filter(customerDto -> customer.getAge() >= 18)
                .flatMap(this.service::createCustomer)
                .switchIfEmpty(ApplicationErrors.underAgeUser(customer));
        // return this.service.createCustomer(customer);
    }

    @MutationMapping
    public Mono<CustomerDto> updateCustomer(@Argument Integer id, @Argument("customer") CustomerDto dto) {
        return this.service.updateCustomer(id, dto);
    }

    @MutationMapping
    public Mono<DeleteResponseDto> deleteCustomer(@Argument Integer id) {
        return this.service.deleteCustomer(id);
    }
}
