package com.example.timemanagementapp.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.timemanagementapp.exceptions.InvalidTokenException;
import com.example.timemanagementapp.exceptions.UserAlreadyExistsException;

@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { UserAlreadyExistsException.class })
    public ResponseEntity<Object> UserAlreadyExistsExceptionHandler(
            UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex);
    }

    @ExceptionHandler(value = { InvalidTokenException.class })
    public ResponseEntity<Object> InvalidTokenExceptionHandler(
            InvalidTokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        String errorMessage = "An unexpected error occurred: " + ex.getMessage();
        System.out.println("havef");
        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
