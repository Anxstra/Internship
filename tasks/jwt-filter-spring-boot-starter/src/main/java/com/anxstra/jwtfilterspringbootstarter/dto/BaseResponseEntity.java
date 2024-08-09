package com.anxstra.jwtfilterspringbootstarter.dto;

public class BaseResponseEntity<T> {

    private final int code;

    private final T body;

    private final String message;

    public BaseResponseEntity(int code, T body, String message) {
        this.code = code;
        this.body = body;
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public static <T> BaseResponseEntity<T> ok(T body) {
        return new BaseResponseEntity<>(200, body, null);
    }

    public static BaseResponseEntity<Void> ok() {
        return new BaseResponseEntity<>(200, null, null);
    }
}
