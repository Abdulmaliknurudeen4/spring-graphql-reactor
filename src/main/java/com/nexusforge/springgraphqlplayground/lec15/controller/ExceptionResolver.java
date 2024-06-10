package com.nexusforge.springgraphqlplayground.lec15.controller;

import com.nexusforge.springgraphqlplayground.lec15.exceptions.ApplicationException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class ExceptionResolver implements DataFetcherExceptionResolver {
    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        var ex = toApplicationException(exception);
        return Mono.just(
                List.of(
                        GraphqlErrorBuilder.newError(environment)
                                .message(ex.getMessage())
                                .errorType(ex.getErrorType())
                                .extensions(ex.getExtensions())
                                .build()
                )
        );
    }

    private ApplicationException toApplicationException(Throwable throwable) {
        // this method converts all Exceptions to Application Exceptions just to be safe,
        // if you have unhandled exceptions, it automatically assumes it's an INTERNAL server
        // Error exception.
        return ApplicationException.class.equals(throwable.getClass()) ?
                (ApplicationException) throwable :
                new ApplicationException(throwable.getMessage(), ErrorType.INTERNAL_ERROR, Collections.emptyMap());
    }
}

// catches error and builds object