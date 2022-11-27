package com.spring.boot.demo.service;

import com.spring.boot.demo.model.Student;
import com.spring.boot.demo.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository repo;

    public List<Student> getAllStudents() {
        List<Student> studentsList = repo.findAll();
        return studentsList;
    }

    public Student getStudentByID(int id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new Exception("Student does not exist with id: " + id));
    }
    public Student saveStudent(Student student) {
        repo.save(student);
        return student;
    }

    public void deleteStudent(int id) {
        repo.deleteById(id);
    }

    public Student updateStudent(int id, Student updatedStudent) throws Exception {
        Student oldStudent = repo.findById(id).orElseThrow(() -> new Exception("Student does not exist with id: " + id));

        oldStudent.setName(updatedStudent.getName());

        repo.save(oldStudent);
        return oldStudent;
    }
}
