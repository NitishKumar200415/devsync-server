package com.devsync.devsync_server.exception;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<Map<String, Object>>
    handleValidationException(Exception ex) {

        Map<String, Object> errorResponse =
                new HashMap<>();

        errorResponse.put(
                "success",
                false
        );

        errorResponse.put(
                "message",
                ex.getMessage()
        );

        errorResponse.put(
                "timestamp",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>
    handleException(Exception ex) {

        Map<String, Object> errorResponse =
                new HashMap<>();

        errorResponse.put(
                "success",
                false
        );

        errorResponse.put(
                "message",
                ex.getMessage()
        );

        errorResponse.put(
                "timestamp",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}