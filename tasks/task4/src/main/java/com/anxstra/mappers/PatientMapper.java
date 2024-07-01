package com.anxstra.mappers;

import com.anxstra.entities.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.anxstra.constants.PatientQueryConstants.ADDRESS_PROPERTY;
import static com.anxstra.constants.PatientQueryConstants.BIRTHDAY_PROPERTY;
import static com.anxstra.constants.PatientQueryConstants.FIO_PROPERTY;
import static com.anxstra.constants.PatientQueryConstants.ID_PROPERTY;
import static com.anxstra.constants.PatientQueryConstants.PASSPORT_PROPERTY;
import static com.anxstra.constants.PatientQueryConstants.PHONE_PROPERTY;
import static com.anxstra.utils.QueryUtils.validateProperty;

public class PatientMapper implements RowMapper<Patient> {

    @Override
    public Patient mapRow(ResultSet resultSet) throws SQLException {
        return Patient.builder()
                      .id(validateProperty(resultSet.getInt(ID_PROPERTY)))
                      .fio(validateProperty(resultSet.getString(FIO_PROPERTY)))
                      .phoneNumber(resultSet.getString(PHONE_PROPERTY))
                      .address(resultSet.getString(ADDRESS_PROPERTY))
                      .birthday(validateProperty(resultSet.getDate(BIRTHDAY_PROPERTY)).toLocalDate())
                      .passport(validateProperty(resultSet.getString(PASSPORT_PROPERTY)))
                      .build();
    }
}
