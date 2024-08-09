package com.anxstra.exceptions;

import com.anxstra.jwtfilterspringbootstarter.dto.BaseResponseEntity;
import com.anxstra.jwtfilterspringbootstarter.exception.ExceptionHandlerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.anxstra.jwtfilterspringbootstarter.exception.AppErrors.badRequestOf;


@RestControllerAdvice
public class SecurityExceptionHandler extends ExceptionHandlerAdvice {


    @ExceptionHandler({BadCredentialsException.class})
    protected ResponseEntity<BaseResponseEntity<Void>> handleUserNotFoundException(BadCredentialsException ex) {


        return processApplicationException(badRequestOf(ex.getMessage()));
    }

}
