package com.anxstra.exceptions;

public class AppointmentNotFoundException extends IllegalArgumentException {

    public AppointmentNotFoundException(String msg) {
        super(msg);
    }
}
