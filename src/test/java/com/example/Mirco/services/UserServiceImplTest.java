package com.example.Mirco.services;


import java.util.ArrayList;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


import com.example.Mirco.services.Dto.UserDto;
import com.example.Mirco.services.Enumation.Gender;
import com.example.Mirco.services.service.UserServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	
	@Autowired
	UserServices services;
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test @Order(value = 1)
	public void testCreateUser() {
		    UserDto userDto = new UserDto();
		    
	        userDto.setEmail("test@test.com");
	        userDto.setPassword("password");
	        userDto.setFname("yousef");
	        userDto.setLname("khayi");
	        userDto.setGender(Gender.MALE);
	        userDto.setUserId(utils.generatedUserId(23));
	        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
	        userDto.setAdresses(new ArrayList<>());
	      
	        UserDto userSaved = services.createUser(userDto);
	        Assertions.assertNotNull(userSaved);
	        Assertions.assertNotNull(userSaved.getUserId());
	        Assertions.assertEquals(userSaved.getEmail(), userDto.getEmail());
	        Assertions.assertEquals(userSaved.getFname(), userDto.getFname());
	        Assertions.assertEquals(userSaved.getLname(), userDto.getLname());
	        Assertions.assertEquals(userSaved.getGender(), userDto.getGender());
	        Assertions.assertEquals(userSaved.getAdresses(), userDto.getAdresses());
	        
	}
	@Test
	public void testGetUserById() {
		UserDto userDto = new UserDto();
		    
		userDto.setEmail("yousef.khayi1@cgi.com");
        userDto.setPassword("123456");
        userDto.setFname("yousef");
        userDto.setLname("khayi");
        userDto.setGender(Gender.MALE);
        userDto.setUserId(utils.generatedUserId(23));
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDto.setAdresses(new ArrayList<>());
        UserDto userSaved = services.createUser(userDto);
        UserDto userById = services.userById(userSaved.getUserId());
        Assertions.assertNotNull(userById);
        Assertions.assertNotNull(userById.getUserId());
        Assertions.assertEquals(userById.getEmail(), userSaved.getEmail());
        Assertions.assertEquals(userById.getFname(), userSaved.getFname());
        Assertions.assertEquals(userById.getLname(), userSaved.getLname());
        Assertions.assertEquals(userById.getGender(), userSaved.getGender());
        Assertions.assertEquals(userById.getEncryptedPassword(), userSaved.getEncryptedPassword());
        Assertions.assertEquals(userById.getUserId(), userSaved.getUserId());
	}
	
	@Test
	public void testUpdateUser() {
		UserDto userDto = new UserDto();
	    
		userDto.setEmail("yousef.khayi@cgi.com");
        userDto.setPassword("123456");
        userDto.setFname("yousef");
        userDto.setLname("khayi");
        userDto.setGender(Gender.MALE);
        userDto.setUserId(utils.generatedUserId(23));
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDto.setAdresses(new ArrayList<>());
        UserDto userSaved = services.createUser(userDto);
        userSaved.setFname("med");
        UserDto userUpdated = services.updateUser(userSaved, userSaved.getUserId());
        
        Assertions.assertNotNull(userUpdated);
        Assertions.assertNotNull(userUpdated.getUserId());
        Assertions.assertEquals(userUpdated.getEmail(), userSaved.getEmail());
        Assertions.assertEquals(userUpdated.getFname(), userSaved.getFname());
        Assertions.assertEquals(userUpdated.getLname(), userSaved.getLname());
        Assertions.assertEquals(userUpdated.getGender(), userSaved.getGender());
        Assertions.assertEquals(userUpdated.getEncryptedPassword(), userSaved.getEncryptedPassword());
        Assertions.assertEquals(userUpdated.getUserId(), userSaved.getUserId());
      
	}
	
	@Test
	public void testDeleteUser(){
		UserDto userDto = new UserDto();
	    
		userDto.setEmail("yousef.khayi3@cgi.com");
        userDto.setPassword("123456");
        userDto.setFname("yousef");
        userDto.setLname("khayi");
        userDto.setGender(Gender.MALE);
        userDto.setUserId(utils.generatedUserId(23));
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDto.setAdresses(new ArrayList<>());
        UserDto userSaved = services.createUser(userDto);
		
		services.deleteUser(userSaved.getUserId());
		Assertions.assertThrows(Exception.class, ()->{
			services.userById(userSaved.getUserId());
		});
	}

}
