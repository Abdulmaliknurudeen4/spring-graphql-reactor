package com.nexusforge.springgraphqlplayground.lec15.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@ToString
public class Customer {
    @Id
    private Integer id;
    private String name;
    private Integer age;
    private String city;
}
