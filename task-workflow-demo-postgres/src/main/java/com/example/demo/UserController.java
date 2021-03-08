package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    String createUser(@RequestBody User user) {
        System.out.println("Creating person : " + user);
        this.userRepository.save(user);
        return "Created Person : " + user.getUsername();
    }

    @GetMapping("")
    List<User> users(){
        return this.userRepository.findAll();
    }

}
