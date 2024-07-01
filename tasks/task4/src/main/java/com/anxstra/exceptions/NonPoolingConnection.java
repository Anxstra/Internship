package com.anxstra.exceptions;

public class NonPoolingConnection extends IllegalArgumentException {

    public NonPoolingConnection(String msg) {
        super(msg);
    }
}
