package com.example.processworkflowpostgres.controller;

import com.example.processworkflowpostgres.entity.User;
import com.example.processworkflowpostgres.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/{username}")
    User getUser(@PathVariable(value = "username", required = true) String username) {
        return userRepository.findByUsername(username);
    }

    @PostMapping("")
    User createUser(@RequestBody(required = true) User user) {
        return userRepository.save(user);
    }
}
