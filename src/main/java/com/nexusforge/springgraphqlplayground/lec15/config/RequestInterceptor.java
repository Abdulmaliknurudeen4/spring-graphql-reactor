package com.nexusforge.springgraphqlplayground.lec15.config;

import org.springframework.graphql.server.RSocketGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class RequestInterceptor implements WebGraphQlInterceptor {
    // to access HeaderInformation.
    // pass caller ID
    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        var headers = request.getHeaders().getOrEmpty("caller-id");
        var callerId = headers.isEmpty() ? "" : headers.get(0);
        //modify excution input to return to controller
        request.configureExecutionInput((e,b) -> b.graphQLContext(Map.of("caller-id", callerId
        )).build());
        return chain.next(request);
    }

}
