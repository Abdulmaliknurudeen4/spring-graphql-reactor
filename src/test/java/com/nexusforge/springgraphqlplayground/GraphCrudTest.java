package com.nexusforge.springgraphqlplayground;

import com.nexusforge.springgraphqlplayground.lec16.dto.Status;
import com.nexusforge.springgraphqlplayground.lec16.dto.CustomerDto;
import com.nexusforge.springgraphqlplayground.lec16.dto.DeleteResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
@TestPropertySource(properties = "lec=lec14")
public class GraphCrudTest {

    @Autowired
    private HttpGraphQlTester client;

    @Test
    public void allCustomersTest() {
        var doc = """
                                query {
                        customers{
                        name
                        age
                        }
                }
                                """;
        this.client.document(doc)
                .execute()
                .path("customers")
                .entityList(Object.class).hasSizeGreaterThan(2)
                .path("customers.[0].name").entity(String.class).isEqualTo("John Doe");
    }

    @Test
    public void customerById(){
        this.client.documentName("crud-operations")
                .variable("id", 1)
                .operationName("GetCustomerById")
                .execute()
                .path("response.id").entity(Integer.class).isEqualTo(7)
                .path("response.name").entity(String.class).isEqualTo("John Doe")
                .path("response.age").entity(Integer.class).isEqualTo(30);

    }


    @Test
    public void createCustomer(){
        this.client.documentName("crud-operations")
                .variable("customer", CustomerDto.create(null, "Micheal", 55, "seattle"))
                .operationName("CreateCustomer")
                .execute()
//                .path("response.id").entity(Integer.class).isEqualTo(7)
                .path("response.name").entity(String.class).isEqualTo("Micheal")
                .path("response.age").entity(Integer.class).isEqualTo(55);

    }

    @Test
    public void updateCustomer(){
        this.client.documentName("crud-operations")
                .variable("customer", CustomerDto.create(null, "Obie", 45, "Gwagwalada"))
                .variable("id", 4)
                .operationName("UpdateCustomer")
                .execute()
                .path("response.id").entity(Integer.class).isEqualTo(4)
                .path("response.name").entity(String.class).isEqualTo("Obie")
                .path("response.age").entity(Integer.class).isEqualTo(45);

    }

    @Test
    public void deleteCustomer(){
        this.client.documentName("crud-operations")
                .variable("id", 2)
                .operationName("DeleteCustomer")
                .execute()
                .path("response").entity(DeleteResponseDto.class).satisfies(r ->{
                    Assertions.assertThat(r.getId()).isEqualTo(2);
                    Assertions.assertThat(r.getStatus()).isEqualTo(Status.SUCCESS);
                });

    }
}
