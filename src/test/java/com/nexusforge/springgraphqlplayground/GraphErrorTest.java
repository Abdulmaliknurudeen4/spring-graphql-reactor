package com.nexusforge.springgraphqlplayground;

import com.nexusforge.springgraphqlplayground.lec16.dto.CustomerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
@TestPropertySource(properties = "lec=lec15")
public class GraphErrorTest {

    @Autowired
    private HttpGraphQlTester client;

    @Test
    public void createCustomer() {
        this.client
                .mutate().header("caller-id", "demo").build()
                .documentName("crud-operations")
                .variable("customer", CustomerDto.create(null, "Micheal", 15, "seattle"))
                .operationName("CreateCustomer")
                .execute()
                .errors().satisfy(listerrors -> {
                    Assertions.assertThat(listerrors).hasSize(1);
                    Assertions.assertThat(listerrors.getFirst().getErrorType()).isEqualTo(ErrorType.BAD_REQUEST);
                }).path("response").valueIsNull();

    }

}
