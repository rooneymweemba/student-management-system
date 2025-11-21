package com.example.spring_boot.Student.services;
import com.example.spring_boot.Student.Student;
import com.example.spring_boot.Student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Slf4j
@Service
public class StudentService {


    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }
    public List<Student> getAllStudents() {
        log.info("Fetching all students from repository");
        List<Student> students = studentRepository.findAll();
        log.info("Fetched size -> {} | students [{}]", students.size(), students);
        return students;
    }
    public Optional<Student> findStudentsByID(String id) {
        log.info("Fetching all student with id {} from repository", id);
        Optional<Student> students = studentRepository.findStudentById(id);
        if(students.isPresent()){
        log.info("found student with id {}", id);
        }
        return students;
    }
    public List<Student> sortedStudents(String field){
        log.info("field: {}", field);
        return studentRepository.findAll(Sort.by(field).descending());
    }
    public Page<Student> paginatedStudents(int offset,int pageSize) {
        return studentRepository.findAll(PageRequest.of(offset, pageSize));

    }
    public Page<Student> paginatedAndSortedStudents(int offset,int pageSize, String field) {
        return studentRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field).descending()));

    }

    public void addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentById(student.getId());
        studentRepository.save(student);
        log.info("student with id {} has been added",student.getId()) ;
        if(studentOptional.isPresent()){
            log.error("id has already been given out");
        }

    }


    public void deleteStudent(String id){
        boolean exists = studentRepository.existsById(id);
        if (!exists){
            log.info("No Student with this ID exists");
        }
        studentRepository.deleteById(id);

    }
    @Transactional
    public void updateStudent(String student_id,
                              String name,
                              String status){
        Optional<Student> studentOptional = studentRepository.findById(student_id);
        if(studentOptional.isPresent()){
            log.info("student with {} has been found", student_id);
        }

        Student student = studentOptional.get();


        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)){
            student.setName(name);
            log.info("student with id {} name changed to {} ", student_id, name);
        }
        if (status != null && !status.isEmpty() && !Objects.equals(student.getStatus(), status)){
            student.setStatus(status);
            log.info("student with id {} status changed to {} ", student_id, status);
        }
        LocalDateTime now = LocalDateTime.now();

        String updated_at = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        student.setUpdatedAt(updated_at);
        studentRepository.save(student);


    }

}
