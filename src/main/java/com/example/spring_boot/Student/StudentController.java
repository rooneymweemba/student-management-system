package com.example.spring_boot.Student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<String> registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") String id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("student "+ id + " has been removed  successfully" );
    }
    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateStudent(
        @PathVariable("id") String id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String status){

        studentService.updateStudent(id, name, status);
        List<String> updated = new ArrayList<>();
        if (name != null) {
            updated.add("name");
        }
        if (status != null) {
            updated.add("status");
        }
        String message;
        if (updated.isEmpty()) {
            message = "No fields were updated";
        } else if (updated.size() == 1) {
            message = updated.get(0) + " has been updated";
        } else {
            message = String.join(" and ", updated) + " have been updated";
        }

        return ResponseEntity.ok(message);
    }

}



