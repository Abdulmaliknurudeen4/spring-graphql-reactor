package com.nexusforge.springgraphqlplayground.lec07.service;

import com.nexusforge.springgraphqlplayground.lec07.entity.CustomerWithOrder;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.function.UnaryOperator;

@Service
public class CustomerOrderDataFetcher implements DataFetcher<Flux<CustomerWithOrder>> {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;

    private UnaryOperator<Flux<CustomerWithOrder>> updateOrdersTransformer(boolean includeOrders) {
        // if orders are needed,
        // if not retunr
        return includeOrders ? customerWithOrderFlux -> customerWithOrderFlux.flatMapSequential(this::fetchOrders)
                : customerWithOrderFlux -> customerWithOrderFlux;
    }

    private Mono<CustomerWithOrder> fetchOrders(CustomerWithOrder customer) {
       return this.orderService.ordersByCustomers(customer.getName())
                .collectList()
                .doOnNext(customer::setOrders)
                .thenReturn(customer);
    }

    // wire and build stuff yourself programmatically
    @Override
    public Flux<CustomerWithOrder> get(DataFetchingEnvironment environment) throws Exception {
        var includeOrders = environment.getSelectionSet().contains("orders");
        System.out.println(includeOrders);
        return this.customerService.allCustomer()
                .map(c -> CustomerWithOrder.create(c.getId(), c.getName(), c.getAge(), c.getCity(), Collections.emptyList()))
                .transform(this.updateOrdersTransformer(includeOrders));

    }
}
