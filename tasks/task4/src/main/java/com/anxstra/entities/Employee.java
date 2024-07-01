package com.anxstra.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@EqualsAndHashCode
public class Employee {

    private Integer id;

    private String fio;

    private String phoneNumber;

    private Integer positionId;

    private LocalDate hireDate;

    @Override
    public String toString() {
        return "Employee{" +
                "id = " + id +
                ", ФИО = '" + fio + '\'' +
                ", номер телефона = '" + phoneNumber + '\'' +
                ", id должности = " + positionId +
                ", дата устройства = " + hireDate +
                '}';
    }
}
