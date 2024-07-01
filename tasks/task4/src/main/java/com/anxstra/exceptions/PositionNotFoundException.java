package com.anxstra.exceptions;

public class PositionNotFoundException extends IllegalArgumentException {

    public PositionNotFoundException(String msg) {
        super(msg);
    }
}
