package com.example.spring_boot.Student;

import com.example.spring_boot.Student.validation.ValidateNonNumeric;
import com.example.spring_boot.Student.validation.ValidateStatus;
import jakarta.persistence.*;
import lombok.Data;

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
    @ValidateNonNumeric(message = "Name must not contain numeric characters")
    private String name;
    @Id
    private String id = generateRandomId(10);
    @Size(max = 255)
    private String content;

    @ValidateStatus
    private String status;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;

    public Student(String name, String id, String content, String status, String createdAt, String updatedAt) {
        this.name = name;
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student() {
    }

    public Student(String name, String content, String status) {
        this.name = name;
        this.content = content;
        this.status = status;
    }
    public Student(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @PrePersist
    public void onCreate() {
        if (this.id == null) {
            this.id = generateRandomId(10);

        }
        if (this.status == null || !this.status.equals("PENDING") && !this.status.equals("DONE")){
            this.status = "PENDING";
        }
        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.createdAt = formatted;
        this.updatedAt = formatted;

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