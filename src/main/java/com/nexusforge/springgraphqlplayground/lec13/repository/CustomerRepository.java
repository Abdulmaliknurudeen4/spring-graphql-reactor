package com.nexusforge.springgraphqlplayground.lec13.repository;

import com.nexusforge.springgraphqlplayground.lec13.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
