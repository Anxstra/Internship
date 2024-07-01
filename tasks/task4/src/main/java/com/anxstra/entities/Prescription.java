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
public class Prescription {

    private Integer id;

    private Integer doctorId;

    private Integer patientId;

    private String description;

    private LocalDate issueDate;

    private LocalDate expirationDate;

    @Override
    public String toString() {
        return "Prescription{" +
                "id = " + id +
                ", код доктора = " + doctorId +
                ", код пациента = " + patientId +
                ", описание = '" + description + '\'' +
                ", действителен с = " + issueDate +
                ", действителен до = " + expirationDate +
                '}';
    }
}
