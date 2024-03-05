package com.nexusforge.springgraphqlplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.nexusforge.springgraphqlplayground.sec01.lec05")
public class SpringGraphQlPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphQlPlaygroundApplication.class, args);
    }

}
