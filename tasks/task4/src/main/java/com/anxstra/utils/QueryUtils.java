package com.anxstra.utils;

import com.anxstra.exceptions.ParameterNullException;

import java.util.Objects;

import static com.anxstra.constants.ExceptionMessageConstants.NULL_PARAMETER;

public class QueryUtils {

    private QueryUtils() {
    }

    public static <T> T validateProperty(T value) {
        if (Objects.isNull(value)) {
            throw new ParameterNullException(NULL_PARAMETER);
        }
        return value;
    }
}
