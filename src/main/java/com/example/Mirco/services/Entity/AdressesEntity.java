package com.example.Mirco.services.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AdressesEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String idPublic;
	
	private String city;
	
	private String country;
	
	private String street;
	
	private String postal;
	
	private String type;
	
	@ManyToOne
	private UserEntity user;

	public AdressesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdressesEntity(long id, String idPublic, String city, String country, String street, String postal,
			String type) {
		this.id = id;
		this.idPublic = idPublic;
		this.city = city;
		this.country = country;
		this.street = street;
		this.postal = postal;
		this.type = type;
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

	public UserEntity getUserEntity() {
		return user;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.user = userEntity;
	}
	

}
