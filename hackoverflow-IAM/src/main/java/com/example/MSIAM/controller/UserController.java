package com.example.MSIAM.controller;

import com.example.MSIAM.entity.User;
import com.example.MSIAM.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
public class UserController {

    private UserService userSvc;

    private BCryptPasswordEncoder encoder;

    public UserController(UserService userSvc, BCryptPasswordEncoder encoder) {
        this.userSvc = userSvc;
        this.encoder = encoder;
    }

    /**
     * << Account Creation >>
     * Checks if User already exists in the database
     * Adds user if does not exist
     *
     * @param user
     * @return user
     */
    @PostMapping("/account")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public void addUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUuid(UUID.randomUUID());
        user.setAuthority("Client");
        System.out.println("User Created :");
        System.out.println(user);
        userSvc.createUser(user);
    }
}