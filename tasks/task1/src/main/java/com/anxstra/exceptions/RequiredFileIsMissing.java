package com.anxstra.exceptions;

import java.io.IOException;

public class RequiredFileIsMissing extends IOException {

    public RequiredFileIsMissing(String msg) {
        super(msg);
    }
}
