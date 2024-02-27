package com.nexusforge.springgraphqlplayground.sec01.lec02.entity;

import lombok.Data;

@Data
public class AgeRangeFilter {
    private Integer minAge;
    private Integer maxAge;
}
