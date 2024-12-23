package com.muluneh.MUCSchool.controller;

import com.muluneh.MUCSchool.domain.Teacher;
import com.muluneh.MUCSchool.dto.TeacherDto;
import com.muluneh.MUCSchool.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/teachers/")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("register")
    public ResponseEntity<?> registerTeacher(@RequestBody TeacherDto teacherDto){
        Teacher newTeacher = teacherService.registerTeacher(teacherDto);
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

}
