package com.anxstra.repositories;

import com.anxstra.connection.ConnectionPool;
import com.anxstra.entities.Patient;
import com.anxstra.exceptions.DatabaseAccessException;
import com.anxstra.mappers.PatientMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.anxstra.constants.CommonConstant.FIRST_PARAM_POSITION;
import static com.anxstra.constants.CommonConstant.SQL_EXCEPTION_MESSAGE;
import static com.anxstra.constants.PatientQueryConstants.DELETE_QUERY;
import static com.anxstra.constants.PatientQueryConstants.GET_ALL_BY_DOCTOR_AND_DATE;
import static com.anxstra.constants.PatientQueryConstants.GET_ALL_QUERY;
import static com.anxstra.constants.PatientQueryConstants.GET_BY_ID_QUERY;
import static com.anxstra.constants.PatientQueryConstants.SAVE_QUERY;
import static com.anxstra.constants.PatientQueryConstants.UPDATE_QUERY;

public class PatientRepository implements CRUDRepository<Integer, Patient> {

    private final ConnectionPool connectionPool;

    private final PatientMapper rowMapper;

    public PatientRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        this.rowMapper = new PatientMapper();
    }

    @Override
    public Optional<Patient> getById(Integer id) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement prepStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            prepStatement.setInt(FIRST_PARAM_POSITION, id);
            ResultSet resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(rowMapper.mapRow(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DatabaseAccessException(SQL_EXCEPTION_MESSAGE.formatted(e.getMessage()));
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Patient> getAll() {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement prepStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = prepStatement.executeQuery();
            List<Patient> patients = new ArrayList<>();
            while (resultSet.next()) {
                patients.add(rowMapper.mapRow(resultSet));
            }
            return patients;
        } catch (SQLException e) {
            throw new DatabaseAccessException(SQL_EXCEPTION_MESSAGE.formatted(e.getMessage()));
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void save(Patient entity) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement prepStatement = connection.prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement(prepStatement, entity, false);
            prepStatement.execute();
            ResultSet resultSet = prepStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt(FIRST_PARAM_POSITION));
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException(SQL_EXCEPTION_MESSAGE.formatted(e.getMessage()));
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Patient entity) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement prepStatement = connection.prepareStatement(UPDATE_QUERY)) {
            prepareStatement(prepStatement, entity, true);
            prepStatement.execute();
        } catch (SQLException e) {
            throw new DatabaseAccessException(SQL_EXCEPTION_MESSAGE.formatted(e.getMessage()));
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement prepStatement = connection.prepareStatement(DELETE_QUERY)) {
            prepStatement.setInt(FIRST_PARAM_POSITION, id);
            prepStatement.execute();
        } catch (SQLException e) {
            throw new DatabaseAccessException(SQL_EXCEPTION_MESSAGE.formatted(e.getMessage()));
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    public List<Patient> getAllByDoctorAndDate(Integer doctorId, LocalDate from, LocalDate to) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement prepStatement = connection.prepareStatement(GET_ALL_BY_DOCTOR_AND_DATE)) {
            int paramPos = FIRST_PARAM_POSITION;
            prepStatement.setDate(paramPos++, Date.valueOf(from));
            prepStatement.setDate(paramPos++, Date.valueOf(to));
            prepStatement.setInt(paramPos, doctorId);
            ResultSet resultSet = prepStatement.executeQuery();
            List<Patient> patients = new ArrayList<>();
            while (resultSet.next()) {
                patients.add(rowMapper.mapRow(resultSet));
            }
            return patients;
        } catch (SQLException e) {
            throw new DatabaseAccessException(SQL_EXCEPTION_MESSAGE.formatted(e.getMessage()));
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void prepareStatement(PreparedStatement prepStatement,
                                  Patient patient, boolean isUpdate) throws SQLException {

        int paramPos = FIRST_PARAM_POSITION;
        prepStatement.setString(paramPos++, patient.getFio());
        prepStatement.setString(paramPos++, patient.getPhoneNumber());
        prepStatement.setString(paramPos++, patient.getAddress());
        prepStatement.setDate(paramPos++, Date.valueOf(patient.getBirthday()));
        prepStatement.setString(paramPos++, patient.getPassport());
        if (isUpdate) {
            prepStatement.setInt(paramPos, patient.getId());
        }
    }
}
