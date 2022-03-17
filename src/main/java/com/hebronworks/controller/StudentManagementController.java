package com.hebronworks.controller;


import com.hebronworks.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path="management/api/v1/students")
public class StudentManagementController {
    private static List<Student> STUDENTS = Arrays.asList(new Student(1, "George hebbron"), new Student(2, "Joh Allan"), new Student(3, "Anna Smith"));

    @GetMapping
    public static List<Student> getAllStudents() {
        return STUDENTS;
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @PutMapping(path = "{studentId}")
    public void editStudent(@PathVariable Integer studentId,@RequestBody Student student){
        System.out.printf("%s %s",studentId,student);

    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable Integer studentId){
        System.out.println(studentId);
    }
}
