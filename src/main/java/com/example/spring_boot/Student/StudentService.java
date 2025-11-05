package com.example.spring_boot.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student){
        studentRepository.findStudentByName(student.getName());
        Optional<Student> studentOptional = studentRepository.findStudentByName(student.getName());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("name taken");

        }
        studentRepository.save(student);
        System.out.println(student);
    }


    public void deleteStudent(String id){
        boolean exists = studentRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("student with id " + id + " does not exist");
        }
        studentRepository.deleteById(id);

    }
    @Transactional
    public void updateStudent(String student_id,
                              String name,
                              String status){
        Student student = studentRepository.findById(student_id)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + student_id + " doesn't exist"
                ));
        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)){
            Optional<Student> findStudentByEmail = studentRepository.findStudentByName(student.getName());
            student.setName(name);
        }
        if (status != null && !status.isEmpty() && !Objects.equals(student.getStatus(), status)){
            student.setStatus(status);
        }



    }

}
