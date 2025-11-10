package com.example.spring_boot.Student.repository;

import com.example.spring_boot.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student>findStudentById(String id);

}
