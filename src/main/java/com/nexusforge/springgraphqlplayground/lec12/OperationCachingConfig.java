package com.nexusforge.springgraphqlplayground.lec12;

import graphql.ExecutionInput;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Configuration
public class OperationCachingConfig {

    // suggestions: use variables along with operation name
    // use variables and don't cache
    // use caffiene cache to store cached queries

    @Bean
    public GraphQlSourceBuilderCustomizer customizer(PreparsedDocumentProvider provider) {
        return c -> c.configureGraphQl(builder -> builder.preparsedDocumentProvider(provider));
    }

    @Bean
    public PreparsedDocumentProvider preparsedDocumentProvider() {

        Map<String, PreparsedDocumentEntry> map = new ConcurrentHashMap<>();
        return new PreparsedDocumentProvider() {
            // can you use redis ???
            @Override
            public PreparsedDocumentEntry getDocument(ExecutionInput executionInput,
                                                      Function<ExecutionInput, PreparsedDocumentEntry> parseAndValidateFunction) {
                return map.computeIfAbsent(executionInput.getQuery(), key -> {
                    System.out.println("Not Found " + key);
                    var r = parseAndValidateFunction.apply(executionInput);
                    System.out.println("Hashing");
                    return r;
                });

            }
        };
    }
}

/*
  if (map.containsKey(executionInput.getQuery())) {
        return map.get(executionInput.getQuery());
        }
PreparsedDocumentEntry preparsedDocumentEntry = parseAndValidateFunction.apply(executionInput);
                map.put(executionInput.getQuery(), preparsedDocumentEntry);
        return preparsedDocumentEntry;*/
