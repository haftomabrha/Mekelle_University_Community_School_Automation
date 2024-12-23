package com.muluneh.MUCSchool.controller;

import com.muluneh.MUCSchool.domain.Account;
import com.muluneh.MUCSchool.domain.Student;
import com.muluneh.MUCSchool.dto.StudentDto;
import com.muluneh.MUCSchool.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")

public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentDto studentDto){
        Student student = studentService.registerStudent(studentDto);
      return ResponseEntity.ok(student);
    }
}
