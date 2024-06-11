package com.nexusforge.springgraphqlplayground.lec16.dto;

import lombok.Data;

@Data
public class CustomerDto implements CustomerResponseInterface{
    private Integer id;
    private String name;
    private Integer age;
    private String city;
}
