package com.deliveryproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DataBaseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UniqueConstraintException.class)
    private ResponseEntity<RestErrorMessage> uniqueConstraintException(UniqueConstraintException uniqueConstraintException){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST,
                uniqueConstraintException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }
}
