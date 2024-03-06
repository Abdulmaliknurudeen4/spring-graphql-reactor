package com.nexusforge.springgraphqlplayground.lec08;

import com.nexusforge.springgraphqlplayground.lec09.dto.AllTypes;
import com.nexusforge.springgraphqlplayground.lec09.dto.Car;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Controller
public class ScalarController {
    private final AllTypes allTypes = AllTypes.create(
            UUID.randomUUID(),
            10,
            10.12f,
            "atlanta",
            false,
            120000000000L,
            Byte.valueOf("12"),
            Short.valueOf("100"),
            BigDecimal.valueOf(123456789.123456789),
            BigInteger.valueOf(123456789),
            LocalDate.now(),
            LocalTime.now(),
            OffsetDateTime.now(),
            Car.HONDA
    );

    @QueryMapping("getAllTypes")
    public Mono<AllTypes> get() {
        System.out.println(allTypes.toString());
        return Mono.just(allTypes);
    }
}
