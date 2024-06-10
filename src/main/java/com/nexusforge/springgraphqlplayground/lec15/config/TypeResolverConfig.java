package com.nexusforge.springgraphqlplayground.lec15.config;

import com.nexusforge.springgraphqlplayground.lec15.dto.CustomerDto;
import graphql.schema.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class TypeResolverConfig {
    // to map CustomerDto to customerType.

    @Bean
    public TypeResolver typeResolver() {
        ClassNameTypeResolver resolver = new ClassNameTypeResolver();
        resolver.addMapping(CustomerDto.class, "Customer");
        return resolver;
    }

    @Bean
    public RuntimeWiringConfigurer configurer(TypeResolver resolver){
        return c -> c.type("CustomerResponse", builder -> builder.typeResolver(resolver));
    }
}
