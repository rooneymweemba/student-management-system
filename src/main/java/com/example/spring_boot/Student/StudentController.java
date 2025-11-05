package com.example.spring_boot.Student;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
 
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") String id){
        studentService.deleteStudent(id);
    }
    @PutMapping(path = "{id}")
    public void updateStudent(
        @PathVariable("id") String id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String status){

        studentService.updateStudent(id, name, status);

    }


 
}
