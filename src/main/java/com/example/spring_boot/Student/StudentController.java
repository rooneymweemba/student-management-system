package com.example.spring_boot.Student;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
 
import org.springframework.web.bind.annotation.RequestMapping;
 
import java.time.LocalDate;
import java.util.List;
 
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    @GetMapping
    public List<Student> getStudents() {
        return List.of(
                new Student(
                        "Rooney",
                        "1",
                        "Good day",
                        LocalDate.now().toString(),
                        LocalDate.now().toString()
                )
        );
    }
 
}
