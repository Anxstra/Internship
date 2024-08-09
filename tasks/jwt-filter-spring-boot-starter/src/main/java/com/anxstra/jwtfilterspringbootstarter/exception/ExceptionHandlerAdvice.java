package com.anxstra.jwtfilterspringbootstarter.exception;

import com.anxstra.jwtfilterspringbootstarter.dto.BaseResponseEntity;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

import static com.anxstra.jwtfilterspringbootstarter.exception.AppErrors.badRequestOf;

public abstract class ExceptionHandlerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> handleConstraintViolation(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult().getFieldErrors().stream()
                           .map(error -> error.getField() + " " + error.getDefaultMessage())
                           .collect(Collectors.joining(", "));

        logger.error(message, ex);

        return processApplicationException(badRequestOf(message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> handleConstraintViolation(ConstraintViolationException ex) {

        String message = ex.getConstraintViolations()
                           .stream()
                           .map(violation -> {
                               String[] path = violation.getPropertyPath().toString().split("\\.");
                               return path[path.length - 1] + " " + violation.getMessage();
                           })
                           .collect(Collectors.joining(", "));

        logger.error(message, ex);

        return processApplicationException(badRequestOf(message));
    }

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<BaseResponseEntity<Void>> handleApplicationException(ApplicationException ex) {

        logger.error(ex.getMessage(), ex);

        return processApplicationException(ex.getError());
    }

    protected ResponseEntity<BaseResponseEntity<Void>> processApplicationException(AppError cause) {

        return ResponseEntity.ok()
                             .body(new BaseResponseEntity<>(cause.getCode(), null, cause.getMessage()));
    }
}
