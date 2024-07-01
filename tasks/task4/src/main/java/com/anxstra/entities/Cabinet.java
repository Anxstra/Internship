package com.anxstra.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@EqualsAndHashCode
public class Cabinet {

    private Integer id;

    private String name;

    private String number;

    @Override
    public String toString() {
        return "Cabinet{" +
                "id = " + id +
                ", название = '" + name + '\'' +
                ", номер = '" + number + '\'' +
                '}';
    }
}
