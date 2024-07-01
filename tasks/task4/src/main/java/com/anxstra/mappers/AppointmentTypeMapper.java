package com.anxstra.mappers;

import com.anxstra.entities.AppointmentType;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.anxstra.constants.AppointmentTypeQueryConstants.ID_PROPERTY;
import static com.anxstra.constants.AppointmentTypeQueryConstants.NAME_PROPERTY;
import static com.anxstra.utils.QueryUtils.validateProperty;

public class AppointmentTypeMapper implements RowMapper<AppointmentType> {

    @Override
    public AppointmentType mapRow(ResultSet resultSet) throws SQLException {
        return AppointmentType.builder()
                              .id(validateProperty(resultSet.getInt(ID_PROPERTY)))
                              .name(validateProperty(resultSet.getString(NAME_PROPERTY)))
                              .build();
    }
}
