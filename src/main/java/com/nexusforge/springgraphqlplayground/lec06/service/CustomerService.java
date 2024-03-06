package com.nexusforge.springgraphqlplayground.lec06.service;

import com.nexusforge.springgraphqlplayground.lec06.entity.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

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
        return flux.delayElements(Duration.ofSeconds(1)).doOnNext(c -> this.print("customer : "  + c.getName()));
    }

    private void print(String msg){
        System.out.println(LocalDateTime.now() + " : " +msg);
    }
}
