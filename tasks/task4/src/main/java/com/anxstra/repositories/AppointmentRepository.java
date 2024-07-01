package com.anxstra.repositories;

import com.anxstra.connection.ConnectionPool;
import com.anxstra.entities.Appointment;
import com.anxstra.exceptions.DatabaseAccessException;
import com.anxstra.mappers.AppointmentMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.anxstra.constants.AppointmentQueryConstants.DELETE_QUERY;
import static com.anxstra.constants.AppointmentQueryConstants.GET_ALL_QUERY;
import static com.anxstra.constants.AppointmentQueryConstants.GET_BY_ID_QUERY;
import static com.anxstra.constants.AppointmentQueryConstants.SAVE_QUERY;
import static com.anxstra.constants.AppointmentQueryConstants.UPDATE_QUERY;
import static com.anxstra.constants.CommonConstant.FIRST_PARAM_POSITION;
import static com.anxstra.constants.CommonConstant.SQL_EXCEPTION_MESSAGE;

public class AppointmentRepository implements CRUDRepository<Integer, Appointment> {

    private final ConnectionPool connectionPool;

    private final AppointmentMapper rowMapper;

    public AppointmentRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        this.rowMapper = new AppointmentMapper();
    }

    @Override
    public Optional<Appointment> getById(Integer id) {
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
    public List<Appointment> getAll() {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement prepStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = prepStatement.executeQuery();
            List<Appointment> appointments = new ArrayList<>();
            while (resultSet.next()) {
                appointments.add(rowMapper.mapRow(resultSet));
            }
            return appointments;
        } catch (SQLException e) {
            throw new DatabaseAccessException(SQL_EXCEPTION_MESSAGE.formatted(e.getMessage()));
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void save(Appointment entity) {
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
    public void update(Appointment entity) {
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

    private void prepareStatement(PreparedStatement prepStatement,
                                  Appointment appointment, boolean isUpdate) throws SQLException {

        int paramPos = FIRST_PARAM_POSITION;
        prepStatement.setInt(paramPos++, appointment.getPatientId());
        prepStatement.setInt(paramPos++, appointment.getDoctorId());
        prepStatement.setInt(paramPos++, appointment.getCabinetId());
        prepStatement.setInt(paramPos++, appointment.getTypeId());
        prepStatement.setTimestamp(paramPos++, Timestamp.valueOf(appointment.getDate()));
        if (isUpdate) {
            prepStatement.setInt(paramPos, appointment.getId());
        }
    }
}
