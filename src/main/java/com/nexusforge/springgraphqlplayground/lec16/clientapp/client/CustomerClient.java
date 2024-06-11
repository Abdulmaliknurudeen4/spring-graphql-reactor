package com.nexusforge.springgraphqlplayground.lec16.clientapp.client;

import com.nexusforge.springgraphqlplayground.lec16.dto.CustomerDto;
import com.nexusforge.springgraphqlplayground.lec16.dto.GenericResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerClient {

    private final HttpGraphQlClient client;

    public CustomerClient(@Value("${customer.service.url}") String baseUrl) {
        this.client = HttpGraphQlClient.builder()
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
        return this.client.documentName("customer-by-id")
                .variable("id", id)
                .execute()
                .mapNotNull(cr -> {
                    var field = cr.field("customerById");
                    /*if(field.getValue() != null){
                        return new GenericResponse<>(field.toEntity(CustomerDto.class));
                    }else{
                        return new GenericResponse<>(field.getErrors());
                    }*/
                    return field.hasValue() ? new GenericResponse<>(field.toEntity(CustomerDto.class)) :
                             new GenericResponse<>(field.getErrors());

                });
    }

}
