package com.anxstra.exceptions;

public class AppointmentTypeNotFoundException extends IllegalArgumentException {

    public AppointmentTypeNotFoundException(String msg) {
        super(msg);
    }
}
