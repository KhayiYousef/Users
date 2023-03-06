package com.example.Mirco.services.security;
	
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.Mirco.services.Dto.UserDto;
import com.example.Mirco.services.service.UserServices;

import java.util.Date;

@Component
public class JwtUtil {
	@Autowired
	UserServices userServices;

    public String generateToken(UserDetails userDetails) {
        
        return createToken(userDetails.getUsername());
    }

    
    private String createToken( String subject) {
    	final UserDto userDto = userServices.userByEmail(subject);
        final Date now = new Date();
        return Jwts.builder()
        		.claim("id", userDto.getUserId())
        		.claim("lname", userDto.getLname())
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXCEPTION_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.TOKEN_SECRET)
                .compact();
    }

   
}

