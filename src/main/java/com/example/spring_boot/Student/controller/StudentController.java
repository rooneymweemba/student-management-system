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
import org.springframework.util.StringUtils;
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

    @DeleteMapping(path = "{student_id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("student_id") String id){
        studentService.deleteStudent(id);
        HttpStatus status = HttpStatus.NO_CONTENT;
        ApiResponse response = new ApiResponse("Student deleted successfully",status);
        return new  ResponseEntity<>(response,status);
    }
    @PutMapping(path = "updateStudent/{student_id}")
    public ResponseEntity<ApiResponse> updateStudent(@Valid
        @PathVariable("student_id") String student_id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String status,
        @RequestParam(required = false) String content) {
        log.info("received update student request -> {}", student_id);
        studentService.updateStudent(student_id, name, status, content);
        List<String> updated = new ArrayList<>();

        if (StringUtils.hasText(name)) {
            updated.add("name");
        }
        if (StringUtils.hasText(status)) {
            updated.add("status");
        }
        if (StringUtils.hasText(content)) {
            updated.add("content");
        }

        String fieldsUpdated;
        String incompleteMessage = "";
        if (updated.size() == 1) {
            fieldsUpdated = updated.get(0);
        } else {
            fieldsUpdated = String.join(", ", updated.subList(0, updated.size() - 1))
                    + " and "
                    + updated.get(updated.size() - 1);
            incompleteMessage = fieldsUpdated + (updated.size() == 1 ? " has been updated" : " have been updated");
        }
        HttpStatus httpStatus = HttpStatus.OK;
        String message = String.format("Student with id %s has been updated ",student_id) + incompleteMessage;
        ApiResponse response = new ApiResponse(message,httpStatus);
        return new  ResponseEntity<>(response,httpStatus);
    }

}



