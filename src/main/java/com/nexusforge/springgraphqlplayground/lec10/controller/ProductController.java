package com.nexusforge.springgraphqlplayground.lec10.controller;

import com.nexusforge.springgraphqlplayground.lec10.dto.Book;
import com.nexusforge.springgraphqlplayground.lec10.dto.Electronics;
import com.nexusforge.springgraphqlplayground.lec10.dto.FruitDto;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class ProductController {

    @QueryMapping
    public Flux<Object> products(){
        return Flux.just(
                FruitDto.create(
                        UUID.randomUUID(),
                        "Banana",
                        145,
                        LocalDate.now()),
                Book.create(
                        UUID.randomUUID(),
                        "Harry Potter",
                        156,
                        "larry Page"),
                Electronics.create(
                        UUID.randomUUID(),
                        "Mac Book",
                        189,
                        "APPLE"
                )
        );
    }
}
