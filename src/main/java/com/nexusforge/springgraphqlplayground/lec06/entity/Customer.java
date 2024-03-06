package com.nexusforge.springgraphqlplayground.lec06.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Customer {
    private Integer id;
    private String name;
    private Integer age;
    private String city;

}
