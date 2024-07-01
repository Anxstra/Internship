package com.anxstra.mappers;

import com.anxstra.entities.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.anxstra.constants.AppointmentQueryConstants.CABINET_PROPERTY;
import static com.anxstra.constants.AppointmentQueryConstants.DATE_PROPERTY;
import static com.anxstra.constants.AppointmentQueryConstants.DOCTOR_PROPERTY;
import static com.anxstra.constants.AppointmentQueryConstants.ID_PROPERTY;
import static com.anxstra.constants.AppointmentQueryConstants.PATIENT_PROPERTY;
import static com.anxstra.constants.AppointmentQueryConstants.TYPE_PROPERTY;
import static com.anxstra.utils.QueryUtils.validateProperty;

public class AppointmentMapper implements RowMapper<Appointment> {

    @Override
    public Appointment mapRow(ResultSet resultSet) throws SQLException {
        return Appointment.builder()
                          .id(validateProperty(resultSet.getInt(ID_PROPERTY)))
                          .patientId(validateProperty(resultSet.getInt(PATIENT_PROPERTY)))
                          .doctorId(validateProperty(resultSet.getInt(DOCTOR_PROPERTY)))
                          .cabinetId(validateProperty(resultSet.getInt(CABINET_PROPERTY)))
                          .typeId(validateProperty(resultSet.getInt(TYPE_PROPERTY)))
                          .date(validateProperty(resultSet.getTimestamp(DATE_PROPERTY)).toLocalDateTime())
                          .build();
    }
}
