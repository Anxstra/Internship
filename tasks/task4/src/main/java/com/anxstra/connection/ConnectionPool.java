package com.anxstra.connection;

import com.anxstra.exceptions.NonPoolingConnection;
import com.anxstra.exceptions.OutOfConnectionsExceptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.anxstra.constants.ExceptionMessageConstants.NON_POOLING_CONNECTION;
import static com.anxstra.constants.ExceptionMessageConstants.OUT_OF_CONNECTION;

public class ConnectionPool {

    private static final int POOL_SIZE = 10;

    private static final String URL_FORMAT = "jdbc:postgresql://%s:%s/%s";

    private final List<Connection> connections;

    private final List<Connection> usedConnections;

    private ConnectionPool(List<Connection> connections) {
        this.connections = connections;
        this.usedConnections = new ArrayList<>();
    }

    public static ConnectionPool create(ConnectionProperties properties) throws SQLException {
        String url = String.format(URL_FORMAT, properties.getHost(), properties.getPort(), properties.getDatabase());

        List<Connection> pool = new ArrayList<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(createConnection(url, properties.getUser(), properties.getPassword()));
        }

        return new ConnectionPool(pool);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        if (connections.isEmpty()) {
            throw new OutOfConnectionsExceptions(OUT_OF_CONNECTION);
        }
        Connection connection = connections.remove(connections.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (usedConnections.remove(connection)) {
            connections.add(connection);
        } else {
            throw new NonPoolingConnection(NON_POOLING_CONNECTION);
        }
    }
}
