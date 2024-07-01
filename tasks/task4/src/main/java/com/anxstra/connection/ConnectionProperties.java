package com.anxstra.connection;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConnectionProperties {

    private String host;

    private String port;

    private String user;

    private String password;

    private String database;
}
