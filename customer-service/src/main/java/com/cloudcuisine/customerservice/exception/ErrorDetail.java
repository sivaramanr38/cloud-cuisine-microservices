package com.cloudcuisine.customerservice.exception;

public class ErrorDetail {
    private String code;      // e.g., CustomerNotFound
    private String message;   // More specific explanation

    public ErrorDetail(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
