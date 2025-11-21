package com.example.spring_boot.Student.controller;
import com.example.spring_boot.Student.globalExceptionHandler.DatabaseOperationException;
import com.example.spring_boot.Student.globalExceptionHandler.StudentNotFoundException;
import com.example.spring_boot.Student.responseDTO.ApiResponse;
import com.example.spring_boot.Student.Student;
import com.example.spring_boot.Student.services.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping(path = "api/v1/student")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        try {
            return studentService.getAllStudents();
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("Lost access to database", e);
        }
    }
    @GetMapping(path = "findById/{id}")
    public Student getStudentById(@PathVariable("id") String id) {
        return studentService.findStudentsByID(id).orElseThrow(() -> new StudentNotFoundException("Student not found", null));

    }
    @GetMapping(path = "{field}")
    public List<Student> getSortedStudents(@PathVariable("field") String field) {
        try {
            return studentService.sortedStudents(field);
        } catch (DataAccessException e) {
           throw new DatabaseOperationException("Lost access to database", e);
        }
    }
    @GetMapping(path = "/pagination/{offset}/{pageSize}")
    public Page<Student> paginatedStudents(@PathVariable int offset, @PathVariable int pageSize) {
        return studentService.paginatedStudents(offset, pageSize);
    }
    @GetMapping(path = "/paginationAndSort/{offset}/{pageSize}/{field}")
    public Page<Student> paginatedAndSortedStudents(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        return studentService.paginatedAndSortedStudents(offset, pageSize, field);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> registerNewStudent(@Valid @RequestBody Student student){
        log.info("received new student request -> {}", student);
            studentService.addNewStudent(student);
            ApiResponse response = new ApiResponse("Student added successfully", HttpStatus.CREATED);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("id") String id){
        studentService.deleteStudent(id);
        HttpStatus status = HttpStatus.NO_CONTENT;
        ApiResponse response = new ApiResponse("Student deleted successfully",status);
        return new  ResponseEntity<>(response,status);
    }
    @PutMapping(path = "{id}")
    public ResponseEntity<ApiResponse> updateStudent(@Valid
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



