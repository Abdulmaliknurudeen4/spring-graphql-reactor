package com.nexusforge.springgraphqlplayground.lec15.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Service
public class HeaderCheckFilter implements WebFilter {
    //implementation of rejecting request...
    // Just webflux. not graphql... rejecting things on transport layer
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var isEmpty = exchange.getRequest().getHeaders().getOrEmpty("caller-id").isEmpty();
        return !isEmpty ? chain.filter(exchange)
                : Mono.fromRunnable(()->exchange.getResponse()
                .setStatusCode(HttpStatus.BAD_REQUEST));
    }
}
/*
GraphQl doesn't have it's own security protocol, it depends on the transport layer,
maybe Using WebFlux or Other things.*/
