package com.example.authjwt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authjwt.security.model.JwtRequestModel;
import com.example.authjwt.security.model.JwtResponseModel;
import com.example.authjwt.security.service.AuthService; 

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Protected World 🌍";
    }
    
    @GetMapping("/hi")
    public String hi() {
        return "Hi Public World 🌍";
    }
}
