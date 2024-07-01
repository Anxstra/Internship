package com.anxstra.exceptions;

public class EmployeeNotFoundException extends IllegalArgumentException {

    public EmployeeNotFoundException(String msg) {
        super(msg);
    }
}
