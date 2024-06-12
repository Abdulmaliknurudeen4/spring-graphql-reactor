package com.nexusforge.springgraphqlplayground;

import com.nexusforge.springgraphqlplayground.lec16.dto.Action;
import com.nexusforge.springgraphqlplayground.lec16.dto.CustomerEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.graphql.test.tester.WebSocketGraphQlTester;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureHttpGraphQlTester
@TestPropertySource(properties = "lec=lec15")
public class GraphSubscriptionTest {

    private static final String WS_PATH = "ws://localhost:8080/graphql";
    @Autowired
    private HttpGraphQlTester client;

    @Test
    public void subscriptionTest() {
        var webSocketClient = WebSocketGraphQlTester
                .builder(WS_PATH, new ReactorNettyWebSocketClient()).build();

        // delete a customer
        this.client.documentName("crud-operations")
                .variable("id", 6)
                .operationName("DeleteCustomer")
                .executeAndVerify(); // no errors in the response

        webSocketClient.documentName("subscription-test")
                .executeSubscription()
                .toFlux("customerEvents", CustomerEvent.class)
                .take(1)
                .as(StepVerifier::create)
                .consumeNextWith(e -> Assertions.assertThat(e.getAction()).isEqualTo(Action.DELETED))
                .verifyComplete();

    }

}
