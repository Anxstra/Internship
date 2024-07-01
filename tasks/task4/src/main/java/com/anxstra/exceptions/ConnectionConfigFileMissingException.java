package com.anxstra.exceptions;

import java.io.IOException;

public class ConnectionConfigFileMissingException extends IOException {

    public ConnectionConfigFileMissingException(String msg) {
        super(msg);
    }
}
