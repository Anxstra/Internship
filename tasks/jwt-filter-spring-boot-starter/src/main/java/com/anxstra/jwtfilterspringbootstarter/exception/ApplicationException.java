package com.anxstra.jwtfilterspringbootstarter.exception;

public class ApplicationException extends RuntimeException {

    private final AppError error;

    public ApplicationException(AppError error) {
        this.error = error;
    }

    public AppError getError() {
        return error;
    }
}
