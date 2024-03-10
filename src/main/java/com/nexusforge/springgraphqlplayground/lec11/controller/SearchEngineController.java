package com.nexusforge.springgraphqlplayground.lec11.controller;

import com.nexusforge.springgraphqlplayground.lec11.dto.Book;
import com.nexusforge.springgraphqlplayground.lec11.dto.Electronics;
import com.nexusforge.springgraphqlplayground.lec11.dto.FruitDto;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

@Controller
public class SearchEngineController {

    List<Object> list = List.of(
            FruitDto.create("Fruit", LocalDate.now()
            ),
            Book.create("Harry Potter", "larry Page"),
            Book.create("My Word is Iron", "Ghengis Khan"),
            Book.create("War", "Author Milton"),
            Electronics.create(UUID.randomUUID(), "Mac Book", 189, "APPLE"),
            Electronics.create(UUID.randomUUID(), "Lumia 109", 189, "MICROSOFT"),
            Electronics.create(UUID.randomUUID(), "Mac Book", 189, "APPLE"),
            Electronics.create(UUID.randomUUID(), "AWS COFOUNder", 189, "AWS")
    );

    @QueryMapping
    public Flux<Object> search() {
        return Mono.fromSupplier(() -> new ArrayList<>(list))
                .doOnNext(Collections::shuffle)
                .flatMapIterable(Function.identity())
                .take(ThreadLocalRandom.current().nextInt(0, list.size()));
    }
}
