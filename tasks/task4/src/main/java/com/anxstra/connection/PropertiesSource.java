package com.anxstra.connection;

import com.anxstra.exceptions.ConnectionConfigFileMissingException;
import com.anxstra.exceptions.PropertyMissingException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static com.anxstra.constants.ExceptionMessageConstants.CONFIG_FILE_MISSING;
import static com.anxstra.constants.ExceptionMessageConstants.CONFIG_PROPERTY_MISSING;

public class PropertiesSource {

    private static final String HOST_PROPERTY = "host";

    private static final String PORT_PROPERTY = "port";

    private static final String USER_PROPERTY = "user";

    private static final String PASSWORD_PROPERTY = "password";

    private static final String DATABASE_PROPERTY = "database";

    private static final String CONFIG_FILE_NAME = "application.properties";

    private static ConnectionProperties properties;

    private PropertiesSource() {
    }

    public static ConnectionProperties getProperties() throws IOException {
        if (Objects.isNull(properties)) {
            loadProperties();
        }
        return properties;
    }

    private static void loadProperties() throws IOException {
        Properties dbProps = new Properties();
        InputStream inputStream = Thread.currentThread()
                                        .getContextClassLoader()
                                        .getResourceAsStream(CONFIG_FILE_NAME);

        if (Objects.isNull(inputStream)) {
            throw new ConnectionConfigFileMissingException(CONFIG_FILE_MISSING.formatted(CONFIG_FILE_NAME));
        }
        dbProps.load(inputStream);
        properties = ConnectionProperties.builder()
                                         .host(readProperty(dbProps, HOST_PROPERTY))
                                         .port(readProperty(dbProps, PORT_PROPERTY))
                                         .user(readProperty(dbProps, USER_PROPERTY))
                                         .password(readProperty(dbProps, PASSWORD_PROPERTY))
                                         .database(readProperty(dbProps, DATABASE_PROPERTY))
                                         .build();
    }

    private static String readProperty(Properties propertiesFile, String property) {
        String value = propertiesFile.getProperty(property);
        if (Objects.isNull(value)) {
            throw new PropertyMissingException(CONFIG_PROPERTY_MISSING.formatted(property));
        }
        return value;
    }
}
