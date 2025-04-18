package com.example.authjwt.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authjwt.models.UserModel;
import com.example.authjwt.repository.UserRepository;
import com.example.authjwt.security.model.JwtRequestModel;
import com.example.authjwt.security.model.JwtResponseModel;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

// Service class responsible for handling authentication and registration logic
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

    /**
     * Handles user login by authenticating credentials and generating JWT token
     * 
     * @param request - The login request model containing email and password
     * @return JwtResponseModel - A response containing the generated JWT token
     * @throws BadCredentialsException if authentication fails
     */
    public JwtResponseModel login(JwtRequestModel request) {
        try {
            // Authenticate the user using email and password
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            // If successful, get the authenticated user
            UserDetails user = (UserDetails) auth.getPrincipal();

            // Generate JWT token and return it
            return new JwtResponseModel(jwtService.generateToken(user));
        } catch (AuthenticationException e) {
            // Return a custom error if login fails
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    /**
     * Handles user registration by saving a new user in the database
     * 
     * @param request - The registration request model containing email and password
     * @return String - Success message if user is saved
     * @throws RuntimeException if registration fails (e.g., email already exists)
     */
    public String register(JwtRequestModel request) {
        try {
            // Create a new user entity
            UserModel user = new UserModel();
            user.setEmail(request.getEmail());

            // Encrypt the password before saving
            user.setPassword(encoder.encode(request.getPassword()));

            // Save the user to the database
            userRepo.save(user);

            return "Registered successfully";
        } catch (Exception ex) {
            // Throw an error if saving fails
            throw new RuntimeException("Registration failed: " + ex.getMessage());
        }
    }
}
