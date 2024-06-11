package com.nexusforge.springgraphqlplayground.lec16.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class IsaSuperUser implements CustomerResponseInterface{
    private Integer id;
    private final String messsage="Permission Denied. User cannot be queried.";
}
