package com.nexusforge.springgraphqlplayground.lec12;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class GraphqlController {


    @QueryMapping("sayHello")
    public Mono<String> sayHelloTo(@Argument("name") String name) {
        return Mono.fromSupplier(() -> "Hello " + name);
    }


}
