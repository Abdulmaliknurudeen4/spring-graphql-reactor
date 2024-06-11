package com.nexusforge.springgraphqlplayground.lec16.clientapp.client;

import com.nexusforge.springgraphqlplayground.lec16.dto.CustomerDto;
import com.nexusforge.springgraphqlplayground.lec16.dto.CustomerNotFound;
import com.nexusforge.springgraphqlplayground.lec16.dto.CustomerResponseInterface;
import com.nexusforge.springgraphqlplayground.lec16.dto.GenericResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

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

    public Mono<List<CustomerDto>> allCustomers(){
        return this.client.documentName("crud-operations")
                .operationName("GetAll")
                .retrieve("response")
                .toEntityList(CustomerDto.class);
    }

    public Mono<CustomerDto> cusomterById(Integer id ){
        return this.client.documentName("crud-operations")
                .operationName("GetCustomerbyId")
                .variable("id", id)
                .retrieve("response")
                .toEntity(CustomerDto.class);
    }

}
