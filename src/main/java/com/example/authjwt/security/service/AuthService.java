package com.example.authjwt.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authjwt.models.UserEntity;
import com.example.authjwt.repository.UserRepository;
import com.example.authjwt.role.Role;
import com.example.authjwt.security.model.JwtRequestModel;
import com.example.authjwt.security.model.JwtResponseModel;

//auth/AuthService.java
@Service
public class AuthService {

 @Autowired
 private AuthenticationManager authManager;

 @Autowired
 private UserRepository userRepo;

 @Autowired
 private PasswordEncoder encoder;

 @Autowired
 private JwtService jwtService;

 public JwtResponseModel login(JwtRequestModel request) {
     Authentication auth = authManager.authenticate(
             new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
     UserDetails user = (UserDetails) auth.getPrincipal();
     return new JwtResponseModel(jwtService.generateToken(user));
 }

 public String register(JwtRequestModel request) {
     UserEntity user = new UserEntity();
     user.setUsername(request.getUsername());
     user.setPassword(encoder.encode(request.getPassword()));
     user.setRole(Role.USER);
     userRepo.save(user);
     return "Registered successfully";
 }
}
