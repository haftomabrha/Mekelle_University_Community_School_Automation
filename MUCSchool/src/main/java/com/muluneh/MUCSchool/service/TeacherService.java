package com.muluneh.MUCSchool.service;

import com.muluneh.MUCSchool.domain.Account;
import com.muluneh.MUCSchool.domain.Authority;
import com.muluneh.MUCSchool.domain.Course;
import com.muluneh.MUCSchool.domain.Teacher;
import com.muluneh.MUCSchool.dto.TeacherDto;
import com.muluneh.MUCSchool.repository.AccountRepository;
import com.muluneh.MUCSchool.repository.AuthorityRepository;
import com.muluneh.MUCSchool.repository.StudentRepository;
import com.muluneh.MUCSchool.repository.TeacherRepository;
import com.muluneh.MUCSchool.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TeacherService {
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    public Teacher registerTeacher(TeacherDto teacherDto) {
        Account account = new Account();
        account.setUsername(teacherDto.getUsername());
        account.setPassword(customPasswordEncoder.getPasswordEncoder().encode(teacherDto.getPassword()));
        account.setAccountCreated(LocalDate.now());
        Account saveAccount = accountRepository.save(account);

        Authority authority = new Authority();
        authority.setAuthority("TEACHER");
        authority.setAccount(saveAccount);
        authorityRepository.save(authority);

        //Course course = new Course();

        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setPhoneNumber(teacherDto.getPhoneNumber());
        teacher.setSection(teacherDto.getSection());
        teacher.setCourse(teacherDto.getCourse());
        teacher.setAccount(saveAccount);
    return  teacherRepository.save(teacher);
    }
}
