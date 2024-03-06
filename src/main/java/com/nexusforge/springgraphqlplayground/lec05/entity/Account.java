package com.nexusforge.springgraphqlplayground.lec05.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Account {
    private UUID id;
    private Integer amount;
    private String accountType;
}
