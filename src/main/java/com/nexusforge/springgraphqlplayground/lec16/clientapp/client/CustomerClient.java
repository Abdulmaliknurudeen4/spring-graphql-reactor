package com.nexusforge.springgraphqlplayground.lec16.clientapp.client;

import com.nexusforge.springgraphqlplayground.lec16.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CustomerClient {

    private final HttpGraphQlClient client;

    public CustomerClient(@Value("${customer.service.url}") String baseUrl) {
        this.client = HttpGraphQlClient.builder().header("caller-id-permanent", "MEMEMEMEM")
                .webClient(b -> b.baseUrl(baseUrl))
                .build();
    }

    public Mono<ClientGraphQlResponse> rawQuery(String query) {
        return this.client.document(query).execute();
    }

    /* public Mono<CustomerDto> getCustomerById(Integer id) {
         return this.client.documentName("customer-by-id")
                 .variable("id", id)
                 .retrieve("customerById")
                 .toEntity(CustomerDto.class);
     }*/
    public Mono<GenericResponse<?>> getCustomersById(Integer id) {
        return this.client
                .mutate()
                .header("caller-id-temporal", "Nurudeen AbdulRahman")
                .build()
                .documentName("customer-by-id")
                .variable("id", id)
                .execute()
                .mapNotNull(cr -> {
                    var field = cr.field("customerById");
                    return field.hasValue() ? new GenericResponse<>(field.toEntity(CustomerDto.class)) :
                            new GenericResponse<>(field.getErrors());

                });
    }

    public Mono<CustomerResponseInterface> getCustomersByIdWithUnion(Integer id) {
        return this.client.documentName("customer-by-id")
                .variable("id", id)
                .execute()
                .mapNotNull(cr -> {
                    var field = cr.field("customerById");
                    var isCustomer = "Customer".equals(cr.field("customerById.type").getValue().toString());
                    return isCustomer ? field.toEntity(CustomerDto.class) : field.toEntity(CustomerNotFound.class);

                });
    }

    public Mono<List<CustomerDto>> allCustomers() {
        return this.crud("GetAll", Collections.emptyMap(), new ParameterizedTypeReference<List<CustomerDto>>() {
        });

    }

    public Mono<CustomerDto> cusomterById(Integer id) {
        return this.crud("GetCustomerbyId", Map.of("id", id), new ParameterizedTypeReference<CustomerDto>() {
        });
    }

    public Mono<CustomerDto> createCustomer(CustomerDto dto) {
        return this.crud("CreateCustomer", Map.of("customer", dto), new ParameterizedTypeReference<CustomerDto>() {
        });
    }

    public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto dto) {
        return this.crud("UpdateCustomer", Map.of("id", id,
                "customer", dto), new ParameterizedTypeReference<CustomerDto>() {
        });
    }

    public Mono<DeleteResponseDto> deleteCustomer(Integer id) {
        return this.crud("DeleteCustomer", Map.of("id", id),
                new ParameterizedTypeReference<DeleteResponseDto>() {
        });
    }

    private <T> Mono<T> crud(String operationName, Map<String, Object> variables, ParameterizedTypeReference<T> type) {
        return this.client.documentName("crud-operations")
                .operationName(operationName)
                .variables(variables)
                .retrieve("response")
                .toEntity(type);
    }

}
