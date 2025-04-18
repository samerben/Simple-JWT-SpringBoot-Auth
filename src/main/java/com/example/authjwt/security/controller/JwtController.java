package com.example.authjwt.security.controller;

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
@RequestMapping("/api/auth")
public class JwtController {

 @Autowired
 private AuthService authService;

 @PostMapping("/register")
 public ResponseEntity<?> register(@RequestBody JwtRequestModel request) {
     return ResponseEntity.ok(authService.register(request));
 }

 @PostMapping("/login")
 public ResponseEntity<JwtResponseModel> login(@RequestBody JwtRequestModel request) {
     return ResponseEntity.ok(authService.login(request));
 }
}
