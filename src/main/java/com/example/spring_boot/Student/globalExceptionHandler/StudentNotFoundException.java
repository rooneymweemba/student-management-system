package com.example.spring_boot.Student.globalExceptionHandler;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message, Exception e) {
        super(message,e);
    }
}
