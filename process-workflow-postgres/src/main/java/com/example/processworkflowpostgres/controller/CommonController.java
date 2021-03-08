package com.example.processworkflowpostgres.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
    @GetMapping("/")
    String home() {
        return "Home...";
    }
}
