package com.muluneh.MUCSchool.controller;

import com.muluneh.MUCSchool.domain.Account;
import com.muluneh.MUCSchool.domain.User;
import com.muluneh.MUCSchool.dto.UserDto;
import com.muluneh.MUCSchool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/registrars")
    public ResponseEntity<?> registerRegistrar(@RequestBody UserDto userDto){
        User user = userService.save(userDto);
        //System.out.println("Welcome "+ account.getUsername());
        return ResponseEntity.ok(user);
    }
}
