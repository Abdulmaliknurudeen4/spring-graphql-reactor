package com.nexusforge.springgraphqlplayground.lec06.service;

import com.nexusforge.springgraphqlplayground.lec06.controller.CustomerOrder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
                    CustomerOrder.create(UUID.randomUUID(), "Jake-product-4"))
//            "Mike", List.of(CustomerOrder.create(UUID.randomUUID(), "Mike-product-1"),
//                    CustomerOrder.create(UUID.randomUUID(), "Mike-product-2"),
//                    CustomerOrder.create(UUID.randomUUID(), "Mike-product-3"),
//                    CustomerOrder.create(UUID.randomUUID(), "Mike-product-4")),
//            "John", List.of(CustomerOrder.create(UUID.randomUUID(), "John-product-1"),
//                    CustomerOrder.create(UUID.randomUUID(), "John-product-2"),
//                    CustomerOrder.create(UUID.randomUUID(), "John-product-3"),
//                    CustomerOrder.create(UUID.randomUUID(), "John-product-4")),
//            "Lucy", List.of(CustomerOrder.create(UUID.randomUUID(), "Lucy-product-1"),
//                    CustomerOrder.create(UUID.randomUUID(), "Lucy-product-2"),
//                    CustomerOrder.create(UUID.randomUUID(), "Lucy-product-3"),
//                    CustomerOrder.create(UUID.randomUUID(), "Lucy-product-4"))
    );

    public Flux<CustomerOrder> ordersByCustomers(String name){
        return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(o -> print("orders for " + name));
    }
    private void print(String msg){
        System.out.println(LocalDateTime.now() + " : " +msg);
    }
}
