package com.anxstra.repositories;

import com.anxstra.connection.ConnectionPool;
import com.anxstra.entities.Prescription;
import com.anxstra.exceptions.DatabaseAccessException;
import com.anxstra.mappers.PrescriptionMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.anxstra.constants.CommonConstant.FIRST_PARAM_POSITION;
import static com.anxstra.constants.CommonConstant.SQL_EXCEPTION_MESSAGE;
import static com.anxstra.constants.PrescriptionQueryConstants.DELETE_QUERY;
import static com.anxstra.constants.PrescriptionQueryConstants.GET_ALL_QUERY;
import static com.anxstra.constants.PrescriptionQueryConstants.GET_BY_ID_QUERY;
import static com.anxstra.constants.PrescriptionQueryConstants.SAVE_QUERY;
import static com.anxstra.constants.PrescriptionQueryConstants.UPDATE_QUERY;

public class PrescriptionRepository implements CRUDRepository<Integer, Prescription> {

    private final ConnectionPool connectionPool;

    private final PrescriptionMapper rowMapper;

    public PrescriptionRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        this.rowMapper = new PrescriptionMapper();
    }

    @Override
    public Optional<Prescription> getById(Integer id) {
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
    public List<Prescription> getAll() {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement prepStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = prepStatement.executeQuery();
            List<Prescription> prescriptions = new ArrayList<>();
            while (resultSet.next()) {
                prescriptions.add(rowMapper.mapRow(resultSet));
            }
            return prescriptions;
        } catch (SQLException e) {
            throw new DatabaseAccessException(SQL_EXCEPTION_MESSAGE.formatted(e.getMessage()));
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void save(Prescription entity) {
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
    public void update(Prescription entity) {
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
                                  Prescription prescription, boolean isUpdate) throws SQLException {

        int paramPos = FIRST_PARAM_POSITION;
        prepStatement.setInt(paramPos++, prescription.getDoctorId());
        prepStatement.setInt(paramPos++, prescription.getPatientId());
        prepStatement.setString(paramPos++, prescription.getDescription());
        prepStatement.setDate(paramPos++, Date.valueOf(prescription.getIssueDate()));
        prepStatement.setDate(paramPos++, Date.valueOf(prescription.getExpirationDate()));
        if (isUpdate) {
            prepStatement.setInt(paramPos, prescription.getId());
        }
    }
}
