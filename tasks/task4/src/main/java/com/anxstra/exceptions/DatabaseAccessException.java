package com.anxstra.exceptions;

public class DatabaseAccessException extends RuntimeException {

    public DatabaseAccessException(String msg) {
        super(msg);
    }
}
