package com.anxstra.exceptions;

public class PrescriptionNotFoundException extends IllegalArgumentException {

    public PrescriptionNotFoundException(String msg) {
        super(msg);
    }

}
