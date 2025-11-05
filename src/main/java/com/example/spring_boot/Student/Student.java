package com.example.spring_boot.Student;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
// the data annotation from lombok generates getters and setters automatically
@Data
@Entity
@Table
public class Student {



    private String name;
    @Id
    @Column(length = 10, nullable = false, unique = true)
    private String id = generateRandomId(10);
    private String content;
    private String status;

    private String date = new java.util.Date().toString();
    private String created_at = date;
    private String updated_at = date;

    public Student(String name, String id, String content, String status, String created_at, String updated_at) {
        this.name = name;
        this.id = id;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Student() {
    }

    public Student(String name, String content, String status, String created_at, String updated_at) {
        this.name = name;
        this.content = content;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @PrePersist
    public void generateStudentId() {
        if (this.id == null) {
            this.id = generateRandomId(10);
        }
    }

    // ID generator method
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