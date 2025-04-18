package com.example.authjwt.utils;



import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.authjwt.security.service.JwtService;
import com.example.authjwt.security.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;


@Component
public class JwtFilter extends OncePerRequestFilter {
   @Autowired
   private JwtUserDetailsService userDetailsService;
   @Autowired
   private JwtService jwtService;
   @Override
   protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

      String tokenHeader = request.getHeader("Authorization");
      String username = null;
      String token = null;
      // if bearer token is provided, get the username 	  
      if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
         token = tokenHeader.substring(7);
         try {
            username = jwtService.extractUsername(token);
         } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
         } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
         }
      } else {
         System.out.println("Bearer String not found in token");
      }
      // validate the JWT Token and create a new authentication token and set in security context	  
      if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
         UserDetails userDetails = userDetailsService.loadUserByUsername(username);
         if (jwtService.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken
               authenticationToken = new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());
               authenticationToken.setDetails(new
                  WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
         }
      }
      filterChain.doFilter(request, response);
   }
}