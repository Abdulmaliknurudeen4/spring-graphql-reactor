package com.nexusforge.springgraphqlplayground.sec01.lec05.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Address {
    private String street;
    private String city;
}
