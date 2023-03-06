package com.example.Mirco.services.Dto;



public class AdressesDto {

	private long id;
	
	private String idPublic;
	
	private String city;
	
	private String country;
	
	private String street;
	
	private String postal;
	
	private String type;
	
	private UserDto user;

	public AdressesDto(long id, String idPublic, String city, String country, String street, String postal, String type) {
		super();
		this.id = id;
		this.idPublic = idPublic;
		this.city = city;
		this.country = country;
		this.street = street;
		this.postal = postal;
		this.type = type;
	}

	public AdressesDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdPublic() {
		return idPublic;
	}

	public void setIdPublic(String idPublic) {
		this.idPublic = idPublic;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	
	
	
}
