package com.hebronworks.controller;

import com.hebronworks.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private static List<Student> STUDENTS = Arrays.asList(new Student(1, "George hebbron"), new Student(2, "Joh Allan"), new Student(3, "Anna Smith"));
    @GetMapping(path="{studentId}")
    public Student getStudent(@PathVariable Integer studentId) {
        return STUDENTS.stream().filter(student -> student.getId().equals(studentId)).findFirst().
                orElseThrow(() -> new IllegalArgumentException("No such Student"));
    }

    @DeleteMapping(path="{studentId}")
    public Student deleteStudent(@PathVariable Integer studentId){
        return STUDENTS.stream().filter(student -> student.getId().equals(studentId)).findFirst().
                orElseThrow(() -> new IllegalArgumentException("No such Student"));
    }

    @PutMapping(path="{studentId}")
    public Student editStudent(@PathVariable Integer studentId){
        return STUDENTS.stream().filter(student -> student.getId().equals(studentId)).findFirst().
                orElseThrow(() -> new IllegalArgumentException("No such Student"));
    }

    @PostMapping
    public String addaStudent(@RequestBody Student student){
        return String.format("%s %s",student.getId(),student.getName());
    }


}
