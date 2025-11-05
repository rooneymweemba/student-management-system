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
    @DeleteMapping(path = "{student_id}")
    public void deleteStudent(@PathVariable("student_id") String id){
        studentService.deleteStudent(id);
    }
    @PutMapping(path = "{student_id}")
    public void updateStudent(
        @PathVariable("student_id") String student_id,
        @RequestParam(required = false) String Student_name,
        @RequestParam(required = false) String status){

        studentService.updateStudent(student_id, Student_name, status);

    }

    @GetMapping(path = "/hello")
    public ResponseEntity<?> sayHello() {
        return ResponseBody.ok("Hello, World!");
    }
 
}
