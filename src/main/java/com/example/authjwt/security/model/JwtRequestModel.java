package com.example.authjwt.security.model;


import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size; 
public class JwtRequestModel implements Serializable { 
   /** 
   * 
   */ 
   private static final long serialVersionUID = 2636936156391265891L; 

   @Email(message = "Invalid email format")
   @NotBlank(message = "Email is required")
   private String email;

   @NotBlank(message = "Password is required")
   @Size(min = 6, message = "Password must be at least 6 characters long")
   private String password;
   
   
   public JwtRequestModel() { 
   } 
   public JwtRequestModel(String username, String password) { 
      super(); 
      this.email = email; this.password = password; 
   }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



}