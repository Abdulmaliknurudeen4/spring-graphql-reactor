package com.nexusforge.springgraphqlplayground.lec05.service;

import com.nexusforge.springgraphqlplayground.lec05.entity.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {
    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "sam", 20),
            Customer.create(2, "Jake", 230),
            Customer.create(3, "Mike", 60),
            Customer.create(4, "John", 13),
            Customer.create(5, "Lucy", 2)
    );

    public Flux<Customer> allCustomer() {
        return flux;
    }

}
