package com.muluneh.MUCSchool.service;

import com.muluneh.MUCSchool.domain.Account;
import com.muluneh.MUCSchool.domain.Authority;
import com.muluneh.MUCSchool.domain.User;
import com.muluneh.MUCSchool.dto.UserDto;
import com.muluneh.MUCSchool.repository.AccountRepository;
import com.muluneh.MUCSchool.repository.AuthorityRepository;
import com.muluneh.MUCSchool.repository.UserRepository;
import com.muluneh.MUCSchool.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
    @Transactional
    public User save(UserDto userDto){
        try {
            // Check if an account with the given email already exists
            Account searchAccount = accountRepository.findByUsername(userDto.getEmail()).orElse(null);
            if (searchAccount == null) {

                // Create and save a new Account
                Account account = new Account();
                account.setUsername(userDto.getEmail());
                account.setAccountCreated(LocalDate.now());
                account.setPassword(customPasswordEncoder.getPasswordEncoder().encode(userDto.getFirstName()));
                Account savedAccount = accountRepository.save(account);

                // Assign the appropriate authority/role (e.g., "USER")
                Authority authority = new Authority();
                authority.setAuthority("ROLE_REGISTRAR"); // Change "USER" to the desired role if needed
                authority.setAccount(savedAccount);
                authorityRepository.save(authority);

                // Map UserDto fields to User entity and set the associated account
                User user = new User();
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                user.setEmail(userDto.getEmail());
                user.setPhoneNumber(userDto.getPhoneNumber());
                user.setAccount(savedAccount);

                // Save and return the user
                return userRepository.save(user);
            } else {
                throw new IllegalStateException("Email already taken.");
            }
        } catch (Exception ex) {
            // Handle exceptions as necessary
            return null;
        }
    }

}
