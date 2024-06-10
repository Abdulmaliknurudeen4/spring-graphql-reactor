package com.nexusforge.springgraphqlplayground.lec16.clientapp.service;

import com.nexusforge.springgraphqlplayground.lec16.clientapp.client.CustomerClient;
import com.nexusforge.springgraphqlplayground.lec16.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class ClientDemo implements CommandLineRunner {

    @Autowired
    private CustomerClient client;

    @Override
    public void run(String... args) throws Exception {
        rawQueryDemo().subscribe();
    }

    private Mono<Void> rawQueryDemo() {
        String query = """
                {
                 a: customers{
                 id
                 name
                 age
                 city
                 }
                }
                """;
        Mono<List<CustomerDto>> mono = this.client.rawQuery(query)
                .map(cr -> cr.field("a").toEntityList(CustomerDto.class));
        return mono.delayElement(Duration.ofSeconds(1))
                .doFirst(() -> System.out.println("Raw Query"))
                .then(mono)
                .doOnNext(System.out::println)
                .then();

    }
}
