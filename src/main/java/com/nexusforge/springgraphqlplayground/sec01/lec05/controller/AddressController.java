package com.nexusforge.springgraphqlplayground.sec01.lec05.controller;

import com.nexusforge.springgraphqlplayground.sec01.lec05.entity.Address;
import com.nexusforge.springgraphqlplayground.sec01.lec05.entity.Customer;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {

    @SchemaMapping(typeName = "Customer")
    public Mono<Address> address(Customer customer, DataFetchingFieldSelectionSet selectionSet) {
        System.out.println(
                "address" + selectionSet.getFields()
        );

        return Mono.just(Address.create(customer.getName()
                + "street", customer.getName() + "city"));
    }
}
