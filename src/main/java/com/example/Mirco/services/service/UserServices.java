package com.example.Mirco.services.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.Mirco.services.Dto.UserDto;

public interface UserServices extends UserDetailsService{
	
	public UserDto createUser(UserDto userDto);
	
	public UserDto userById(String id);
	
	public UserDto updateUser(UserDto userDto,String id);
	
	public void deleteUser(String id);
	
	public UserDto userByEmail(String email);
	
	public List<UserDto> AllUsers();
	
	public List<UserDto> femaleUsers();
}
