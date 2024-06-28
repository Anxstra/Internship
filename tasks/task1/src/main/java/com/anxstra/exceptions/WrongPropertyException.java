package com.anxstra.exceptions;

import java.io.IOException;

public class WrongPropertyException extends IOException {

    public WrongPropertyException(String message) {
        super(message);
    }
}
