package com.anxstra.exceptions;

import java.io.IOException;

public class FieldIsMissingException extends IOException {

    public FieldIsMissingException(String msg) {
        super(msg);
    }
}
