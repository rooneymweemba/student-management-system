package com.example.spring_boot.Student;

import com.example.spring_boot.Student.validation.validateNonNumeric;
import com.example.spring_boot.Student.validation.validateStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.validation.constraints.*;

// the data annotation from lombok generates getters and setters automatically
@Data
@Entity
@Table
public class Student {
    @NotBlank
    @Size(max = 30)
    @validateNonNumeric(message = "Name must not contain numeric characters")
    private String name;
    @Id
    private String id = generateRandomId(10);
    @Size(max = 255)
    private String content;

    @validateStatus
    private String status;
    private String created_at ;
    private String updated_at ;

    public Student(String name, String id, String content, String status, String created_at, String updated_at) {
        this.name = name;
        this.id = id;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Student() {
    }

    public Student(String name, String content, String status) {
        this.name = name;
        this.content = content;
        this.status = status;
    }

    @PrePersist
    public void onCreate() {
        if (this.id == null) {
            this.id = generateRandomId(10);

        }
        if (this.status == null){
            this.status = "PENDING";
        }
        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.created_at = formatted;
        this.updated_at = formatted;

    }

    // ID generator meth
    private String generateRandomId(int length) {
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * digits.length());
            sb.append(digits.charAt(index));
        }
        return sb.toString();
    }




}