package com.anxstra.mappers;

import com.anxstra.entities.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.anxstra.constants.EmployeeQueryConstants.FIO_PROPERTY;
import static com.anxstra.constants.EmployeeQueryConstants.HIRE_PROPERTY;
import static com.anxstra.constants.EmployeeQueryConstants.ID_PROPERTY;
import static com.anxstra.constants.EmployeeQueryConstants.PHONE_PROPERTY;
import static com.anxstra.constants.EmployeeQueryConstants.POSITION_PROPERTY;
import static com.anxstra.utils.QueryUtils.validateProperty;


public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet) throws SQLException {
        return Employee.builder()
                       .id(validateProperty(resultSet.getInt(ID_PROPERTY)))
                       .fio(validateProperty(resultSet.getString(FIO_PROPERTY)))
                       .phoneNumber(resultSet.getString(PHONE_PROPERTY))
                       .positionId(validateProperty(resultSet.getInt(POSITION_PROPERTY)))
                       .hireDate(validateProperty(resultSet.getDate(HIRE_PROPERTY)).toLocalDate())
                       .build();
    }
}
