package com.nexusforge.springgraphqlplayground.sec01.lec03.service;

import com.nexusforge.springgraphqlplayground.sec01.lec03.entity.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {
    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "sam", 20, "atlanta"),
            Customer.create(2, "Jake", 230, "las vegas"),
            Customer.create(3, "Mike", 60, "miami"),
            Customer.create(4, "John", 13, "Georgia"),
            Customer.create(5, "Lucy", 2, "houston")
    );

    public Flux<Customer> allCustomer() {
        return flux;
    }

}
