package com.example.spring_boot.Student;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private HttpStatus status;
}
