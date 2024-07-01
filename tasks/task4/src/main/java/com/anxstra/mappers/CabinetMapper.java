package com.anxstra.mappers;

import com.anxstra.entities.Cabinet;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.anxstra.constants.CabinetQueryConstants.ID_PROPERTY;
import static com.anxstra.constants.CabinetQueryConstants.NAME_PROPERTY;
import static com.anxstra.constants.CabinetQueryConstants.NUMBER_PROPERTY;
import static com.anxstra.utils.QueryUtils.validateProperty;


public class CabinetMapper implements RowMapper<Cabinet> {

    @Override
    public Cabinet mapRow(ResultSet resultSet) throws SQLException {
        return Cabinet.builder()
                      .id(validateProperty(resultSet.getInt(ID_PROPERTY)))
                      .name(validateProperty(resultSet.getString(NAME_PROPERTY)))
                      .number(validateProperty(resultSet.getString(NUMBER_PROPERTY)))
                      .build();
    }
}
