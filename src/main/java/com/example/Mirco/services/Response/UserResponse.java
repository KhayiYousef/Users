package com.example.Mirco.services.Response;


import java.util.List;

import com.example.Mirco.services.Enumation.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse{

	private String userId;
	@JsonProperty("name")
	private String fname;
	private String lname;
	private Gender gender;
	private String email;
	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponse(String userId, String fname, String lname, Gender gender,String email, List<AdressesResponse> adresses) {
		super();
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.adresses = adresses;
		this.email = email;
	}
	private List<AdressesResponse> adresses;
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
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
	public List<AdressesResponse> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<AdressesResponse> adresses) {
		this.adresses = adresses;
	}


}
