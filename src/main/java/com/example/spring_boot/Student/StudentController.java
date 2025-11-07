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
    public ResponseEntity<ApiResponse> registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
        ApiResponse response = new ApiResponse("Student added successfully",HttpStatus.CREATED);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("id") String id){
        studentService.deleteStudent(id);
        HttpStatus status = HttpStatus.NO_CONTENT;
        String message = String.format("Student with id %s has been deleted",id);
        ApiResponse response = new ApiResponse(message,status);
        return new  ResponseEntity<>(response,status);
    }
    @PutMapping(path = "{id}")
    public ResponseEntity<ApiResponse> updateStudent(
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
        String incompleteMessage;
        if (updated.isEmpty()) {
            incompleteMessage = "No fields were updated";
        } else if (updated.size() == 1) {
            incompleteMessage = updated.get(0) + " has been updated";
        } else {
            incompleteMessage = String.join(" and ", updated) + " have been updated";
        }

        HttpStatus httpStatus = HttpStatus.OK;
        String message = String.format("Student with id %s has been updated ",id) + incompleteMessage;
        ApiResponse response = new ApiResponse(message,httpStatus);
        return new  ResponseEntity<>(response,httpStatus);
    }

}



