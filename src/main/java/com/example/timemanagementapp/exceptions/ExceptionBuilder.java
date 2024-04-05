package com.example.timemanagementapp.exceptions;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpStatus;

public class ExceptionBuilder {
    public static HashMap<String, Object> buildErrorResponse(HttpStatus status, String error, String messages) {

        final HashMap<String, Object> body = new HashMap<>();
        body.put("path", "/error");
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", messages);
        return body;
    }

    public static HashMap<String, Object> buildErrorResponse(HttpStatus status, String error, List<String> messages) {

        final HashMap<String, Object> body = new HashMap<>();
        body.put("path", "/error");
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", messages);
        return body;
    }

}
