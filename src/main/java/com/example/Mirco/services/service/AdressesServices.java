package com.example.Mirco.services.service;

import java.util.List;

import com.example.Mirco.services.Dto.AdressesDto;

public interface AdressesServices {
	
	AdressesDto addAdresses(AdressesDto adressesDto,String email);
	
	AdressesDto addAdresses(AdressesDto adressesDto);
	
	AdressesDto updateAdresses(AdressesDto adressesDto, String id,String email);
	
	AdressesDto updateAdresses(AdressesDto adressesDto, String id);
	
	List<AdressesDto> allAdresses(String email);
	
	List<AdressesDto> allAdresses();
	
	void deleteAdresses(String id);
	
	AdressesDto adressesById(String id);

}
