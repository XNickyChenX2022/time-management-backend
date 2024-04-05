package com.example.timemanagementapp.exceptions;

import org.springframework.validation.BindingResult;

public class ValidationException extends Exception {
    private final BindingResult errors;

    public ValidationException(BindingResult errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return errors.getAllErrors().getFirst().getDefaultMessage().toString();
    }

}
