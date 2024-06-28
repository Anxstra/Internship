package com.anxstra.entities;

import com.anxstra.entities.enums.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class User {

    private Integer id;

    private String name;

    private String secondName;

    private Sex sex;

    private LocalDate birthday;

    public String getFullName() {
        return name + " " + secondName;
    }
}
