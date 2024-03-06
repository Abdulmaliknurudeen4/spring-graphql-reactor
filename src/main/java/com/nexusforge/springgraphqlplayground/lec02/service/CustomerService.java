package com.nexusforge.springgraphqlplayground.lec02.service;

import com.nexusforge.springgraphqlplayground.lec02.entity.AgeRangeFilter;
import com.nexusforge.springgraphqlplayground.lec02.entity.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public Mono<Customer> customerById(Integer id) {
        return flux.filter(c -> c.getId().equals(id))
                .next();
    }

    public Flux<Customer> nameContains(String name) {
        return flux.filter(c -> c.getName().contains(name));
    }

    public Flux<Customer> filterByAge(AgeRangeFilter filter) {
        return flux.filter(c -> c.getAge() >= filter.getMinAge() && c.getAge() <= filter.getMaxAge());
    }
}
