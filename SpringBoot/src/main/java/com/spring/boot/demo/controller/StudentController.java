package com.spring.boot.demo.controller;

import com.spring.boot.demo.model.Student;
import com.spring.boot.demo.service.StudentService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.spring.boot.demo.model.Student;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService service;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        //fetch all the students
//        StudentService service = new StudentService(); DO NOT USE THIS

        List<Student> rp = service.getAllStudents();
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student newStudent) {
        System.out.println(newStudent.toString());
        Student student = service.saveStudent(newStudent);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudentDetails) throws Exception {
        Student updatedStudent = service.updateStudent(id, updatedStudentDetails);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Integer> deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
