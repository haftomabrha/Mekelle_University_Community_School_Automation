package com.muluneh.MUCSchool.dto;

import com.muluneh.MUCSchool.domain.Course;

import javax.persistence.ManyToOne;

public class TeacherDto extends UserDto {
    private String section;
    private Course course;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
