package com.anxstra.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@EqualsAndHashCode
public class Position {

    private Integer id;

    private String name;

    private BigDecimal salary;

    @Override
    public String toString() {
        return "Position{" +
                "id = " + id +
                ", название должности = '" + name + '\'' +
                ", зарплата = " + salary +
                '}';
    }
}
