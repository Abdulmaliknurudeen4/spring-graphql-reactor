package com.nexusforge.springgraphqlplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.nexusforge.springgraphqlplayground.${lec}")
@EnableR2dbcRepositories(basePackages = "com.nexusforge.springgraphqlplayground.${lec}")
public class SpringGraphQlPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphQlPlaygroundApplication.class, args);
    }

}
