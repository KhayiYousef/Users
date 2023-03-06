package com.example.Mirco.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mirco.services.Dto.UserDto;
import com.example.Mirco.services.Request.UserLoginRequest;
import com.example.Mirco.services.Response.AuthResponse;
import com.example.Mirco.services.security.JwtUtil;
import com.example.Mirco.services.service.UserServices;
@RestController
@CrossOrigin("*")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserServices userServices;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/user/login")
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody UserLoginRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        
        UserDto user = userServices.userByEmail(authRequest.getUsername());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);
        AuthResponse result = new AuthResponse(jwt,user.getUserId());
        return new ResponseEntity<AuthResponse>(result,HttpStatus.OK);
    }
}
