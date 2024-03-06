package com.nexusforge.springgraphqlplayground.lec03.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerOrder {
    private UUID id;
    private String description;

}
