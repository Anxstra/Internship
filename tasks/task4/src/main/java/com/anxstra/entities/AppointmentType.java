package com.anxstra.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AppointmentType {

    private Integer id;

    private String name;

    @Override
    public String toString() {
        return "AppointmentTypes{" +
                "id = " + id +
                ", название = '" + name + '\'' +
                '}';
    }
}
