package com.example.spring_boot.Student.globalExceptionHandler;

import com.example.spring_boot.Student.responseDTO.ApiResponse;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DatabaseOperationException.class)
    public   ResponseEntity<ApiResponse> databaseOperationException(DatabaseOperationException ex) {
       log.error("DatabaseOperationException: {}", ex.getMessage());
       ApiResponse response = new ApiResponse("Database operation failed", HttpStatus.NOT_FOUND);
       return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<ApiResponse> studentNotFoundException(StudentNotFoundException ex) {
        log.error("StudentNotFoundException: {}", ex.getMessage());
        ApiResponse response = new ApiResponse("Student not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage());

        ApiResponse response = new ApiResponse(
                "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
            // Get the first error message only
            String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                    .findFirst()
                    .map(error -> error.getDefaultMessage())
                    .orElse("Validation failed");
            ApiResponse response = new ApiResponse(errorMessage, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }



