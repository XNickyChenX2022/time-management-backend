package com.example.timemanagementapp.controllers.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.example.timemanagementapp.exceptions.ExceptionBuilder.buildErrorResponse;
import com.example.timemanagementapp.exceptions.InvalidTokenException;
import com.example.timemanagementapp.exceptions.TaskNotFoundException;
import com.example.timemanagementapp.exceptions.UserAlreadyExistsException;
import com.example.timemanagementapp.exceptions.ValidationException;
// import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
        private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

        @ExceptionHandler(UserAlreadyExistsException.class)
        public ResponseEntity<Object> UserAlreadyExistsExceptionHandler(
                        UserAlreadyExistsException ex) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                                buildErrorResponse(HttpStatus.CONFLICT, "Conflict", ex.getMessage()));
        }

        @ExceptionHandler(InvalidTokenException.class)
        public ResponseEntity<Object> InvalidTokenExceptionHandler(
                        InvalidTokenException ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                buildErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized",
                                                ex.getMessage()));
        }

        @ExceptionHandler(TaskNotFoundException.class)
        public ResponseEntity<Object> TaskNotFoundExceptionHandler(
                        InvalidTokenException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(buildErrorResponse(HttpStatus.NOT_FOUND, "Not Found",
                                                ex.getMessage()));
        }

        // @ExceptionHandler(value = { IllegalArgumentException.class })
        // public ResponseEntity<Object>
        // IllegalArgumentExceptionHandler(IllegalArgumentException ex) {
        // return ResponseEntity.badRequest().body(
        // buildErrorResponse(HttpStatus.BAD_REQUEST, "Illegal Arguments",
        // ex.getMessage()));

        // }

        @ExceptionHandler({ ValidationException.class })
        public ResponseEntity<Object> handleValidationException(ValidationException ex) {
                return ResponseEntity.badRequest()
                                .body(buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid arguments",
                                                ex.getMessage()));
        }

}
