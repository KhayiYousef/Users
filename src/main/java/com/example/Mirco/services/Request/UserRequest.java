package com.example.Mirco.services.Request;

import java.util.ArrayList;
import java.util.List;

import com.example.Mirco.services.Enumation.Gender;

public class UserRequest{
	
	private String fname;
	private String lname;
	private String email;
	private Gender gender;
	private List<AdressesRequest> adresses = new ArrayList<>();
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	private String password;
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
	public List<AdressesRequest> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<AdressesRequest> adresses) {
		this.adresses = adresses;
	}


}
