package com.nexusforge.springgraphqlplayground.lec16.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerNotFound implements CustomerResponseInterface{
    private Integer id;
    private final String messsage="user not Found";
}
