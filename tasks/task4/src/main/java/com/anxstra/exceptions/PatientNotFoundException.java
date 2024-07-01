package com.anxstra.exceptions;

public class PatientNotFoundException extends IllegalArgumentException {

    public PatientNotFoundException(String msg) {
        super(msg);
    }
}
