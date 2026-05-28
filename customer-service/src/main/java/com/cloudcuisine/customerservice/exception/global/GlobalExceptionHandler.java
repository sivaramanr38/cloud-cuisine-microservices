package com.cloudcuisine.customerservice.exception.global;

import com.cloudcuisine.customerservice.exception.ApiError;
import com.cloudcuisine.customerservice.exception.CustomerNotFoundException;
import com.cloudcuisine.customerservice.exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

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
}
