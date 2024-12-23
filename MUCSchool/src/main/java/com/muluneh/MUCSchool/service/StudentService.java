package com.muluneh.MUCSchool.service;

import com.muluneh.MUCSchool.domain.Account;
import com.muluneh.MUCSchool.domain.Authority;
import com.muluneh.MUCSchool.domain.Student;
import com.muluneh.MUCSchool.dto.StudentDto;
import com.muluneh.MUCSchool.repository.AccountRepository;
import com.muluneh.MUCSchool.repository.StudentRepository;
import com.muluneh.MUCSchool.util.CustomPasswordEncoder;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentService {
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AccountRepository accountRepository;
    public Student registerStudent(StudentDto studentDto) {
       try {
           Account searchAccount =
                   accountRepository.
                           findByUsername(studentDto.getEmail()).orElse(null);
           if (searchAccount == null) {
               Account account = new Account();
               account.setUsername(studentDto.getEmail());
               account.setAccountCreated(LocalDate.now());
               account.setPassword(customPasswordEncoder.getPasswordEncoder().encode(studentDto.getFirstName()));
               Account saveAccount = accountRepository.save(account);

               Authority authority = new Authority();
               authority.setAuthority("STUDENT");
               authority.setAccount(saveAccount);

               Student student = new Student();
               student.setFirstName(studentDto.getFirstName());
               student.setLastName(studentDto.getLastName());
               student.setEmail(studentDto.getEmail());
               student.setPhoneNumber(studentDto.getPhoneNumber());
               student.setParentContact(studentDto.getParentContact());
               student.setAccount(saveAccount);
               return studentRepository.save(student);
           } else {
               throw new IllegalStateException("Email already taken.");
           }
       }
       catch
       (Exception ex){
           return null;
       }


    }
}
