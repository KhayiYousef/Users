package com.example.Mirco.services.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Mirco.services.Enumation.Gender;
import com.example.Mirco.services.Request.UserRequest;
import com.example.Mirco.services.Response.UserResponse;
import com.example.Mirco.services.service.UserServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	UserServices services;
	@Autowired
	UserController controller;
	String userId;
	@Test @Order(1)
	public void addUserTest() {
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail("yousef@gmail.com");
		userRequest.setFname("yousef");
		userRequest.setLname("khayi");
		userRequest.setGender(Gender.MALE);
		userRequest.setPassword("123456");
		ResponseEntity<UserResponse> user = controller.addUser(userRequest);
		userId = user.getBody().getUserId();
		Assertions.assertEquals(user.getStatusCode(), HttpStatus.CREATED);
		Assertions.assertEquals(user.getBody().getEmail(), userRequest.getEmail());
		Assertions.assertEquals(user.getBody().getFname(), userRequest.getFname());
		Assertions.assertEquals(user.getBody().getLname(), userRequest.getLname());
		Assertions.assertEquals(user.getBody().getGender(), userRequest.getGender());
	}
	
	@Test @Order(2)
	public void userByUserIdTest() {
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail("yousef1@gmail.com");
		userRequest.setFname("yousef");
		userRequest.setLname("khayi");
		userRequest.setGender(Gender.MALE);
		userRequest.setPassword("123456");
		ResponseEntity<UserResponse> userSaved = controller.addUser(userRequest);
		ResponseEntity<UserResponse> userId = controller.userByUserId(userSaved.getBody().getUserId());
		Assertions.assertEquals(userId.getStatusCode(), HttpStatus.OK);
		Assertions.assertEquals(userId.getBody().getGender(), userRequest.getGender());
		Assertions.assertEquals(userId.getBody().getEmail(), userRequest.getEmail());
		Assertions.assertEquals(userId.getBody().getFname(), userRequest.getFname());
		Assertions.assertEquals(userId.getBody().getLname(), userRequest.getLname());
	}
	@Test @Order(3)
	public void updateUserTest()
	{
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail("yousef2@gmail.com");
		userRequest.setFname("yousef");
		userRequest.setLname("khayi");
		userRequest.setGender(Gender.MALE);
		userRequest.setPassword("123456");
		ResponseEntity<UserResponse> userSaved = controller.addUser(userRequest);
		userRequest.setFname("oussama");
		ResponseEntity<UserResponse> userUpdated = controller.updateUser(userSaved.getBody().getUserId(),userRequest);
		Assertions.assertEquals(userUpdated.getStatusCode(), HttpStatus.ACCEPTED);
		Assertions.assertEquals(userRequest.getFname(), userUpdated.getBody().getFname());
	}
	@Test @Order(4)
	public void removeUserTest() {
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail("yousef3@gmail.com");
		userRequest.setFname("yousef");
		userRequest.setLname("khayi");
		userRequest.setGender(Gender.MALE);
		userRequest.setPassword("123456");
		ResponseEntity<UserResponse> userSaved = controller.addUser(userRequest);
		ResponseEntity<Object> userRemoved = controller.removeUser(userSaved.getBody().getUserId());
		Assertions.assertEquals(userRemoved.getStatusCode(), HttpStatus.NO_CONTENT);
		Assertions.assertThrows(Exception.class, ()->{
			controller.userByUserId(userSaved.getBody().getUserId());
		});
	}

}
