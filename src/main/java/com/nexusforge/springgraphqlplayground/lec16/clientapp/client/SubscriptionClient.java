package com.nexusforge.springgraphqlplayground.lec16.clientapp.client;

import com.nexusforge.springgraphqlplayground.lec14.dto.CustomerEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.WebSocketGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Flux;

@Service
public class SubscriptionClient {

    private final WebSocketGraphQlClient client;

    public SubscriptionClient(@Value("${customer.events.subscription.url}") String baseUrl) {
        this.client = WebSocketGraphQlClient
                .builder(baseUrl, new ReactorNettyWebSocketClient())
                .build();
    }

    public Flux<CustomerEvent> customerEvents() {
        String query = """
                subscription{
                        customerEvents {
                    id
                    action                    
                                        }
                    }
                    """;
       return this.client
                .document(query)
                .retrieveSubscription("customerEvents")
                .toEntity(CustomerEvent.class);

    }
}
