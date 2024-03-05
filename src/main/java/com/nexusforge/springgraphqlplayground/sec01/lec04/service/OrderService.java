package com.nexusforge.springgraphqlplayground.sec01.lec04.service;

import com.nexusforge.springgraphqlplayground.sec01.lec04.entity.Customer;
import com.nexusforge.springgraphqlplayground.sec01.lec04.controller.CustomerOrder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderService {

    private final Map<String, List<CustomerOrder>> map = Map.of(
            "sam", List.of(CustomerOrder.create(UUID.randomUUID(), "sam-product-1"),
                    CustomerOrder.create(UUID.randomUUID(), "sam-product-2"),
                    CustomerOrder.create(UUID.randomUUID(), "sam-product-3"),
                    CustomerOrder.create(UUID.randomUUID(), "sam-product-4")),
            "Jake", List.of(CustomerOrder.create(UUID.randomUUID(), "Jake-product-1"),
                    CustomerOrder.create(UUID.randomUUID(), "Jake-product-2"),
                    CustomerOrder.create(UUID.randomUUID(), "Jake-product-3"),
                    CustomerOrder.create(UUID.randomUUID(), "Jake-product-4")),
            "Mike", List.of(CustomerOrder.create(UUID.randomUUID(), "Mike-product-1"),
                    CustomerOrder.create(UUID.randomUUID(), "Mike-product-2"),
                    CustomerOrder.create(UUID.randomUUID(), "Mike-product-3"),
                    CustomerOrder.create(UUID.randomUUID(), "Mike-product-4")),
            "John", List.of(CustomerOrder.create(UUID.randomUUID(), "John-product-1"),
                    CustomerOrder.create(UUID.randomUUID(), "John-product-2"),
                    CustomerOrder.create(UUID.randomUUID(), "John-product-3"),
                    CustomerOrder.create(UUID.randomUUID(), "John-product-4")),
            "Lucy", List.of(CustomerOrder.create(UUID.randomUUID(), "Lucy-product-1"),
                    CustomerOrder.create(UUID.randomUUID(), "Lucy-product-2"),
                    CustomerOrder.create(UUID.randomUUID(), "Lucy-product-3"),
                    CustomerOrder.create(UUID.randomUUID(), "Lucy-product-4"))
    );

    public Flux<CustomerOrder> ordersByCustomers(String name) {
        return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
    }

    public Flux<List<CustomerOrder>> ordersByCustomersNames(List<String> name) {
        return Flux.fromIterable(name)
                .flatMapSequential(this::fetchOrders).defaultIfEmpty(Collections.emptyList());
    }

    //some soruce
    private Mono<List<CustomerOrder>> fetchOrders(String name) {
        return Mono.justOrEmpty(map.get(name)).delayElement(
                Duration.ofMillis(ThreadLocalRandom.current().nextInt(0, 500))
        );
    }

    public Mono<Map<Customer, List<CustomerOrder>>> fetchOrderAsMap(List<Customer> customers){
        return Flux.fromIterable(customers)
                .map(c -> Tuples.of(c, map.getOrDefault(c.getName(), Collections.emptyList())))
                .collectMap(Tuple2::getT1,
                        Tuple2::getT2);
    }
}
