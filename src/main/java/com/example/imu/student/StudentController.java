package com.example.imu.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentController {
    @GetMapping("/query")
    public ResponseEntity<Student> getStudent(@RequestParam Long id, @RequestParam String firstName, @RequestParam String lastName){
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        return ResponseEntity.ok()
                .header("yep","tyran")
                .body(student);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }
}
