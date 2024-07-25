package com.anxstra.jwtfilterspringbootstarter.exception;

public class AppErrors implements AppError {

    public static final AppErrors BAD_REQUEST = new AppErrors(400, "Bad Request");

    public static final AppErrors UNAUTHORIZED = new AppErrors(401, "Unauthorized");

    public static final AppErrors FORBIDDEN = new AppErrors(403, "Forbidden");

    private final int code;

    private final String message;

    public AppErrors(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static AppErrors badRequestOf(String message) {
        return new AppErrors(400, message);
    }

    public static AppErrors unauthorizedOf(String message) {
        return new AppErrors(401, message);
    }

    public static AppErrors forbiddenOf(String message) {
        return new AppErrors(403, message);
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
