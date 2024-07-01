package com.anxstra.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Appointment {

    private Integer id;

    private Integer patientId;

    private Integer doctorId;

    private Integer cabinetId;

    private Integer typeId;

    private LocalDateTime date;

    @Override
    public String toString() {
        return "Appointment{" +
                "id = " + id +
                ", код пациента = " + patientId +
                ", код сотрудника = " + doctorId +
                ", код кабинета = " + cabinetId +
                ", код типа = " + typeId +
                ", дата и время = " + date +
                '}';
    }
}
