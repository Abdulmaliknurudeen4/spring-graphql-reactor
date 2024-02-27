package com.nexusforge.springgraphqlplayground.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class GraphqlController {

    @QueryMapping("sayHello")
    public Mono<String> helloWorld() {
        return Mono.just("Hello World");
    }
}
