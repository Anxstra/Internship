package com.anxstra.mappers;

import com.anxstra.entities.Prescription;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.anxstra.constants.PrescriptionQueryConstants.DESCRIPTION_PROPERTY;
import static com.anxstra.constants.PrescriptionQueryConstants.DOCTOR_PROPERTY;
import static com.anxstra.constants.PrescriptionQueryConstants.EXPIRATION_DATE_PROPERTY;
import static com.anxstra.constants.PrescriptionQueryConstants.ID_PROPERTY;
import static com.anxstra.constants.PrescriptionQueryConstants.ISSUE_DATE_PROPERTY;
import static com.anxstra.constants.PrescriptionQueryConstants.PATIENT_PROPERTY;
import static com.anxstra.utils.QueryUtils.validateProperty;

public class PrescriptionMapper implements RowMapper<Prescription> {

    @Override
    public Prescription mapRow(ResultSet resultSet) throws SQLException {
        return Prescription.builder()
                           .id(validateProperty(resultSet.getInt(ID_PROPERTY)))
                           .doctorId(validateProperty(resultSet.getInt(DOCTOR_PROPERTY)))
                           .patientId(validateProperty(resultSet.getInt(PATIENT_PROPERTY)))
                           .description(validateProperty(resultSet.getString(DESCRIPTION_PROPERTY)))
                           .issueDate(validateProperty(resultSet.getDate(ISSUE_DATE_PROPERTY)).toLocalDate())
                           .expirationDate(validateProperty(resultSet.getDate(EXPIRATION_DATE_PROPERTY)).toLocalDate())
                           .build();
    }
}
