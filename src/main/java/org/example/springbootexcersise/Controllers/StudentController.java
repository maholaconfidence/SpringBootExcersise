package org.example.springbootexcersise.Controllers;

import jakarta.validation.Valid;
import org.example.springbootexcersise.DTO.StudentRequest;
import org.example.springbootexcersise.DTO.StudentResponse;
import org.example.springbootexcersise.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {


    private final StudentService StudentService;

    public StudentController(StudentService studentService) {
        this.StudentService = studentService;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest request) {



        StudentResponse response =
                StudentService.createStudent(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    //READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable long id) {

        return ResponseEntity.ok(
                StudentService.getStudentById(id)
        );
    }

    //UPDATE
    @PostMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable long id, @Valid @RequestBody StudentRequest request) {
        return ResponseEntity.ok(
                StudentService.updateStudent(id, request)
        );
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        StudentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public  ResponseEntity<List<StudentResponse>> getAllStudents(){
        return ResponseEntity.ok(StudentService.getAllStudents());
    }
}





