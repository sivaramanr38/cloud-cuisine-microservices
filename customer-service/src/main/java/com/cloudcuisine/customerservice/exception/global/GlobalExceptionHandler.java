package com.cloudcuisine.customerservice.exception.global;

import com.cloudcuisine.customerservice.exception.ApiError;
import com.cloudcuisine.customerservice.exception.CustomerNotFoundException;
import com.cloudcuisine.customerservice.exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiError> handleCustomerNotFound(CustomerNotFoundException customerNotFoundException) {
        ErrorDetail errorDetail = new ErrorDetail("CustomerNotFound", "The requested customer resource does not exist.");
        ApiError apiError = new ApiError();
        apiError.setCode(HttpStatus.NOT_FOUND.value());
        apiError.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
        apiError.setMessage(customerNotFoundException.getMessage());
        apiError.setTarget("customerId");
        apiError.setDetails(List.of(errorDetail));
        apiError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ErrorDetail> errorDetails = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorDetail(error.getCode(), error.getDefaultMessage()))
                .toList();

        ApiError apiError = new ApiError();
        apiError.setCode(HttpStatus.BAD_REQUEST.value());
        apiError.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        apiError.setMessage("Validation failed for request");
        apiError.setTarget("requestBody");
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setDetails(errorDetails);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex) {
        ErrorDetail detail = new ErrorDetail("RuntimeError", "Unexpected error occurred");
        ApiError apiError = new ApiError();
        apiError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        apiError.setMessage(ex.getMessage());
        apiError.setTarget("system");
        apiError.setDetails(List.of(detail));
        apiError.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

}
