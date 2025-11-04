package com.example.spring_boot.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring_boot.Student.StudentService;

import java.time.LocalDate;
import java.util.List;
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
    public void updateStudent(String id){
        studentRepository.findById(id);
    }

}
