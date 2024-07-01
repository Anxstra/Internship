package com.anxstra.repositories;

import com.anxstra.connection.ConnectionPool;
import com.anxstra.entities.Cabinet;
import com.anxstra.exceptions.DatabaseAccessException;
import com.anxstra.mappers.CabinetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.anxstra.constants.CabinetQueryConstants.DELETE_QUERY;
import static com.anxstra.constants.CabinetQueryConstants.GET_ALL_QUERY;
import static com.anxstra.constants.CabinetQueryConstants.GET_BY_ID_QUERY;
import static com.anxstra.constants.CabinetQueryConstants.SAVE_QUERY;
import static com.anxstra.constants.CabinetQueryConstants.UPDATE_QUERY;
import static com.anxstra.constants.CommonConstant.FIRST_PARAM_POSITION;
import static com.anxstra.constants.CommonConstant.SQL_EXCEPTION_MESSAGE;

public class CabinetRepository implements CRUDRepository<Integer, Cabinet> {

    private final ConnectionPool connectionPool;

    private final CabinetMapper rowMapper;

    public CabinetRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        this.rowMapper = new CabinetMapper();
    }

    @Override
    public Optional<Cabinet> getById(Integer id) {
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
    public List<Cabinet> getAll() {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement prepStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = prepStatement.executeQuery();
            List<Cabinet> cabinets = new ArrayList<>();
            while (resultSet.next()) {
                cabinets.add(rowMapper.mapRow(resultSet));
            }
            return cabinets;
        } catch (SQLException e) {
            throw new DatabaseAccessException(SQL_EXCEPTION_MESSAGE.formatted(e.getMessage()));
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void save(Cabinet entity) {
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
    public void update(Cabinet entity) {
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
                                  Cabinet cabinet, boolean isUpdate) throws SQLException {

        int paramPos = FIRST_PARAM_POSITION;
        prepStatement.setString(paramPos++, cabinet.getName());
        prepStatement.setString(paramPos++, cabinet.getNumber());
        if (isUpdate) {
            prepStatement.setInt(paramPos, cabinet.getId());
        }
    }
}
