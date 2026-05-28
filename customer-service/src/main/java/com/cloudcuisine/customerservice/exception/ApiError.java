package com.cloudcuisine.customerservice.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
    private int code;              // HTTP status code
    private String status;         // Canonical status (e.g., NOT_FOUND)
    private String message;        // Human-readable message
    private String target;         // Field/resource causing error
    private LocalDateTime timestamp;
    private List<ErrorDetail> details; // Optional structured details

    public ApiError(int code, String status, String message, String target, List<ErrorDetail> details) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.target = target;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    public ApiError() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<ErrorDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ErrorDetail> details) {
        this.details = details;
    }
}
