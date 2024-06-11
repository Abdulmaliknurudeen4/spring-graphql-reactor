package com.nexusforge.springgraphqlplayground.lec16.clientapp.service;

import com.nexusforge.springgraphqlplayground.lec16.clientapp.client.CustomerClient;
import com.nexusforge.springgraphqlplayground.lec16.clientapp.client.SubscriptionClient;
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

    @Autowired
    private SubscriptionClient events;

    @Override
    public void run(String... args) throws Exception {
        this.events.customerEvents()
                .doOnNext(e -> System.out.println("**" + e.getAction()+"**"))
                .subscribe();

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
        return this.executor("Raw Query", mono);


    }

    private Mono<Void> getCustomerById() {
        return this.executor("getCustomerByID", this.client.getCustomersByIdWithUnion(65));
    }

    private <T> Mono<Void> executor(String message, Mono<T> mono) {
        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> System.out.println(message))
                .then(mono)
                .doOnNext(System.out::println)
                .then();
    }

    private Mono<Void> allCustomersDemo(){
        return this.executor("allCustomersDemo", this.client.allCustomers());
    }

    private Mono<Void> customerByIdDemo(){
        return this.executor("customerById", this.client.cusomterById(1));
    }
    private Mono<Void> createCustomer(){
        return this.executor("createCustomer Demo", this.client.createCustomer(CustomerDto.create(null, "obie", 45, "Kogi State")));
    }

    private Mono<Void> updateCustomer(){
        return this.executor("updateCustomer Demo", this.client.updateCustomer(2,
                CustomerDto.create(null, "jackson", 25, "Dallas")));
    }
    private Mono<Void> deleteCustomer(){
        return this.executor("Delete Customer Demo", this.client.deleteCustomer(2));
    }
}
