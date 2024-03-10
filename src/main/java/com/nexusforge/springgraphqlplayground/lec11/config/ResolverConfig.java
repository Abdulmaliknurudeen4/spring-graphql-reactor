package com.nexusforge.springgraphqlplayground.lec11.config;

import com.nexusforge.springgraphqlplayground.lec11.dto.FruitDto;
import graphql.schema.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class ResolverConfig {

    @Bean
    public RuntimeWiringConfigurer configurer(TypeResolver resolver) {
        // adding the custom resolver for the Product type
        return c -> c.type("Result", b -> b.typeResolver(resolver));
    }

    @Bean
    public TypeResolver typeResolver() {
        ClassNameTypeResolver resolver = new ClassNameTypeResolver();
        // adding custom resolver in case class names don't match
        resolver.addMapping(FruitDto.class, "Fruit");
        return resolver;
    }
}
