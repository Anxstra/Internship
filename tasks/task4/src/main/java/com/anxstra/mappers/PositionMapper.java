package com.anxstra.mappers;

import com.anxstra.entities.Position;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.anxstra.constants.PositionQueryConstants.ID_PROPERTY;
import static com.anxstra.constants.PositionQueryConstants.NAME_PROPERTY;
import static com.anxstra.constants.PositionQueryConstants.SALARY_PROPERTY;
import static com.anxstra.utils.QueryUtils.validateProperty;

public class PositionMapper implements RowMapper<Position> {

    @Override
    public Position mapRow(ResultSet resultSet) throws SQLException {
        return Position.builder()
                       .id(validateProperty(resultSet.getInt(ID_PROPERTY)))
                       .name(validateProperty(resultSet.getString(NAME_PROPERTY)))
                       .salary(validateProperty(resultSet.getBigDecimal(SALARY_PROPERTY)))
                       .build();
    }
}
