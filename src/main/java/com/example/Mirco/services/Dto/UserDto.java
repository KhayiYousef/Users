package com.example.Mirco.services.Dto;

import java.util.List;

import com.example.Mirco.services.Enumation.Gender;

public class UserDto{
	
	private long id;
	private String userId;
	private String fname;
	private String lname;
	private Gender gender;
	private String email;
	private String password;
	private String encryptedPassword;
	private List<AdressesDto> adresses;
	
	
	public UserDto() {
		
	}
	public UserDto(long id, String userId, String fname, String lname, Gender gender, String email, String password,
			String encryptedPassword, List<AdressesDto> adresses) {
//		, List<AdressesDto> adresses
		this.id = id;
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.encryptedPassword = encryptedPassword;
		this.adresses = adresses;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public List<AdressesDto> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<AdressesDto> adresses) {
		this.adresses = adresses;
	}
	
	

}
