package com.example.spring_boot.Student;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Student {

    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    private String student_name;
    @Id
    @Column(length = 10, nullable = false, unique = true)

    private String student_id;
    private String content;

    @Column(columnDefinition = "ENUM('DONE', 'PENDING')")
    private String status;

    private String created_at;
    private String updated_at;

    public Student(String name, String id, String content, String status, String created_at, String updated_at) {
        this.student_name = name;
        this.student_id = id;

        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Student() {
    }

    public Student(String name, String content,String status , String created_at, String updated_at) {
        this.student_name = name;
        this.content = content;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    @PrePersist
    public void generateStudentId() {
        if (this.student_id == null) {
            this.student_id = generateRandomId(10);
        }
    }

    // ✅ ID generator method
    private String generateRandomId(int length) {
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * digits.length());
            sb.append(digits.charAt(index));
        }
        return sb.toString();
    }


    public String getName() {
        return student_name;
    }

    public void setName(String name) {
        this.student_name = name;
    }

    public String getId() {
        return student_id;
    }

    public void setId(String id) {
        this.student_id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + student_name + '\'' +
                ", id='" + student_id + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}