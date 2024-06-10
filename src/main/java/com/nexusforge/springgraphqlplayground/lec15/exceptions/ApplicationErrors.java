package com.nexusforge.springgraphqlplayground.lec15.exceptions;

import org.springframework.graphql.execution.ErrorType;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ApplicationErrors {
    public static <T> Mono<T> noSuchUser(Integer id){
        return Mono.error(new ApplicationException(
                "No such User",
                ErrorType.BAD_REQUEST,
                        Map.of(
                                "customerId", id
                        )
                ));
    }
}
