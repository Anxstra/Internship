package com.anxstra.constants;

public class CommonConstants {

    public static final long ACCESS_TOKEN_TTL = 1000L * 60 * 5;

    public static final long REFRESH_TOKEN_TTL = 1000L * 60 * 60 * 24;

    public static final String BEARER_PREFIX = "Bearer ";

    public static final String USER_CACHE_NAME = "users";

    private CommonConstants() {
    }
}
