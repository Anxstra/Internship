package com.anxstra.exceptions;

public class CabinetNotFoundException extends IllegalArgumentException {

    public CabinetNotFoundException(String msg) {
        super(msg);
    }

}
