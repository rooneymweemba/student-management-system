package com.example.spring_boot.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT s FROM Student s WHERE s.student_name = ?1")
    Optional<Student>findStudentByName(String name);
    
}
