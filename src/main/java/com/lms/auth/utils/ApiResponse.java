package com.lms.auth.utils;

import java.util.List;

import lombok.Data;

import lombok.Data;
import java.util.List;
import java.util.Collections;

@Data
public class ApiResponse {

    private boolean success;
    private String message;
    private Object data;
    private Integer statusCode;
    private List<String> errors;

    // -------------------------
    // SUCCESS METHODS
    // -------------------------
    public static ApiResponse success(String message) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage(message);
        response.setStatusCode(200);
        response.setErrors(Collections.emptyList());
        response.setData(null);
        return response;
    }

    public static ApiResponse success(String message, Object data) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        response.setStatusCode(200);
        response.setErrors(Collections.emptyList());
        return response;
    }

    public static ApiResponse success(String message, Object data, Integer statusCode) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        response.setStatusCode(statusCode);
        response.setErrors(Collections.emptyList());
        return response;
    }

    // -------------------------
    // ERROR METHODS
    // -------------------------
    public static ApiResponse error(String message) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setMessage(message);
        response.setStatusCode(400);  // default bad request
        response.setErrors(Collections.singletonList(message));
        response.setData(null);
        return response;
    }

    public static ApiResponse error(String message, List<String> errors) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setMessage(message);
        response.setErrors(errors);
        response.setStatusCode(400);
        response.setData(null);
        return response;
    }

    public static ApiResponse error(String message, List<String> errors, Integer statusCode) {
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setMessage(message);
        response.setErrors(errors);
        response.setStatusCode(statusCode);
        response.setData(null);
        return response;
    }
}

