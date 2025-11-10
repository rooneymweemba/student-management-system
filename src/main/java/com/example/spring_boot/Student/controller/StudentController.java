package com.example.spring_boot.Student.controller;


import com.example.spring_boot.Student.responseDTO.ApiResponse;
import com.example.spring_boot.Student.Student;
import com.example.spring_boot.Student.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
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
    public ResponseEntity<ApiResponse> registerNewStudent(@Valid @RequestBody Student student){
        try {
            studentService.addNewStudent(student);
            ApiResponse response = new ApiResponse("Student added successfully", HttpStatus.CREATED);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            String message = "Something went wrong while adding the student";
            ApiResponse response = new ApiResponse(message, status);
            return new ResponseEntity<>(response, status);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("id") String id){
        studentService.deleteStudent(id);
        HttpStatus status = HttpStatus.NO_CONTENT;
        ApiResponse response = new ApiResponse("Student deleted successfully",status);
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



