package com.example.authjwt.security.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.stereotype.Service;

import com.example.authjwt.repository.UserRepository; 
import com.example.authjwt.models.UserModel;

@Service
public class JwtUserDetailsService implements UserDetailsService { 
	
    @Autowired
    private UserRepository userRepo;
    
    
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(), 
                new ArrayList<>()  // Empty list means no authorities (no roles or permissions)

        );
    }
}