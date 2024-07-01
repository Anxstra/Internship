package com.anxstra.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Patient {

    private Integer id;

    private String fio;

    private String phoneNumber;

    private String address;

    private LocalDate birthday;

    private String passport;

    @Override
    public String toString() {
        return "Patient{" +
                "id = " + id +
                ", ФИО = '" + fio + '\'' +
                ", номер телефона = '" + phoneNumber + '\'' +
                ", домашний адрес = '" + address + '\'' +
                ", дата рождения = " + birthday +
                ", серия и номер паспорта = '" + passport + '\'' +
                '}';
    }
}
