package com.example.spring_boot.Student.responseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private int status;
    public ApiResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }


}
