package com.example.spring_boot.Student.globalExceptionHandler;

public class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException(String message, Exception e) {
        super(message);
    }
}
