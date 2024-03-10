package com.nexusforge.springgraphqlplayground.lec11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Electronics {
    private UUID id = UUID.randomUUID();
    private String description;
    private Integer price;
    private String brand;
}
