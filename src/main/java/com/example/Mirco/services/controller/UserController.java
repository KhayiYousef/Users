package com.example.Mirco.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mirco.services.Dto.AdressesDto;
import com.example.Mirco.services.Dto.UserDto;
import com.example.Mirco.services.Request.UserRequest;
import com.example.Mirco.services.Response.AdressesResponse;
import com.example.Mirco.services.Response.UserResponse;
import com.example.Mirco.services.StructMapper.UserMapper;
import com.example.Mirco.services.service.UserServices;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserServices services;
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
		UserDto userDto = UserMapper.INSTANCE.requestToDto(userRequest);
		UserDto userCreated = services.createUser(userDto);
		UserResponse userResponse = UserMapper.INSTANCE.dtoToResponse(userCreated);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> userByUserId(@PathVariable String id) {
		UserDto userDto = services.userById(id);
		UserResponse userResponse =UserMapper.INSTANCE.dtoToResponse(userDto);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> removeUser(@PathVariable String id) {
		services.deleteUser(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		List<UserDto> userDtos = services.AllUsers();
		List<UserResponse> responses = userDtos.stream()
				.map((UserDto user) -> (
						new UserResponse(user.getUserId(),user.getFname(),user.getLname(),user.getGender(),user.getEmail(),user.getAdresses().stream()
								.map((AdressesDto adresses)->(
										new AdressesResponse(adresses.getIdPublic(),adresses.getCity(),adresses.getCountry(),adresses.getStreet(),adresses.getPostal(),adresses.getType())
										)).toList())
								)).toList();		
		return new ResponseEntity<List<UserResponse>>(responses,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id,@RequestBody UserRequest userRequest) {
		UserDto userDto = UserMapper.INSTANCE.requestToDto(userRequest);
		UserDto userupdeted = services.updateUser(userDto,id);
		UserResponse userResponse = UserMapper.INSTANCE.dtoToResponse(userupdeted);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED);
	}
	@GetMapping("/female")
	public ResponseEntity<List<UserResponse>> femaleUsers()
	{
		List<UserDto> userDtos = services.femaleUsers();
		List<UserResponse> responses = userDtos.stream()
				.map((UserDto user)->(
						new UserResponse(user.getUserId(),user.getFname(),user.getLname(),user.getGender(),user.getEmail(),user.getAdresses().stream()
								.map((AdressesDto adresses)->(new AdressesResponse(adresses.getIdPublic(),adresses.getCity(),adresses.getCountry(),adresses.getStreet(),adresses.getPostal(),adresses.getType()))).toList()
								)
						)).toList();
		return new ResponseEntity<List<UserResponse>>(responses,HttpStatus.OK);
		
	}

}
