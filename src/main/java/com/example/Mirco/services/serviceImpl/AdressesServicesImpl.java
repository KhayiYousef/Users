package com.example.Mirco.services.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Mirco.services.Utils;
import com.example.Mirco.services.Dto.AdressesDto;
import com.example.Mirco.services.Entity.AdressesEntity;
import com.example.Mirco.services.Entity.UserEntity;
import com.example.Mirco.services.Repository.AdressesRepository;
import com.example.Mirco.services.Repository.UserRepository;
import com.example.Mirco.services.StructMapper.AdressesMapper;
import com.example.Mirco.services.service.AdressesServices;

@Service
public class AdressesServicesImpl implements AdressesServices {
	
	@Autowired
	AdressesRepository adressesRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;

	@Override
	public AdressesDto addAdresses(AdressesDto adressesDto,String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		AdressesEntity adressesEntity = AdressesMapper.INSTANCE.dtoToEntity(adressesDto);
		adressesEntity.setIdPublic(utils.generatedUserId(12));
		adressesEntity.setUserEntity(userEntity);
		adressesRepository.save(adressesEntity);
		return AdressesMapper.INSTANCE.entityToDto(adressesEntity);
	}

	@Override
	public AdressesDto updateAdresses(AdressesDto adressesDto, String id,String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		List<AdressesEntity> adressesEntities = userEntity.getAdresses();
		
		AdressesEntity adressesEntity = adressesRepository.findByIdPublic(id);
		
		if(adressesEntities.contains(adressesEntity)==false)throw new RuntimeException("vous n'est l'accés pour ces Adresses");
		
		if(adressesEntity == null) throw new RuntimeException(id);
		AdressesEntity entity = AdressesMapper.INSTANCE.dtoToEntity(adressesDto);
		adressesEntity.setCity(entity.getCity());
		adressesEntity.setCountry(entity.getCountry());
		adressesEntity.setPostal(entity.getPostal());
		adressesEntity.setStreet(entity.getStreet());
		adressesEntity.setType(entity.getType());
		AdressesDto adresses = AdressesMapper.INSTANCE.entityToDto(adressesEntity);
		adressesRepository.save(adressesEntity);
		return adresses;
	}

	@Override
	public List<AdressesDto> allAdresses(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		List<AdressesEntity> adressesEntities = userEntity.getAdresses();
		List<AdressesDto> adressesDtos = new ArrayList<>();
		adressesDtos = adressesEntities.stream()
				.map((AdressesEntity adresses)->(
						new AdressesDto(adresses.getId(),adresses.getIdPublic(),adresses.getCity(),adresses.getCountry(),adresses.getStreet(),adresses.getPostal(),adresses.getType())
						)).toList();
		
		return adressesDtos;
	}

	@Override
	public AdressesDto adressesById(String id) {
		AdressesEntity adressesEntity = adressesRepository.findByIdPublic(id);
		if(adressesEntity == null) throw new RuntimeException(id);
		return AdressesMapper.INSTANCE.entityToDto(adressesEntity);
	}

	@Override
	public AdressesDto addAdresses(AdressesDto adressesDto) {
		AdressesEntity adressesEntity = AdressesMapper.INSTANCE.dtoToEntity(adressesDto);
		adressesEntity.setIdPublic(utils.generatedUserId(12));
		adressesRepository.save(adressesEntity);
		return AdressesMapper.INSTANCE.entityToDto(adressesEntity);
	}

	@Override
	public List<AdressesDto> allAdresses() {
		List<AdressesEntity> adressesEntities = adressesRepository.findAll();
		List<AdressesDto> adressesDtos = new ArrayList<>();
		adressesDtos = adressesEntities.stream()
				.map((AdressesEntity adresses)->(
						new AdressesDto(adresses.getId(),adresses.getIdPublic(),adresses.getCity(),adresses.getCountry(),adresses.getStreet(),adresses.getPostal(),adresses.getType())
						)).toList();
		
		return adressesDtos;
	}

	@Override
	public void deleteAdresses(String id) {
		AdressesEntity adresses = adressesRepository.findByIdPublic(id);
		if(adresses == null) throw new UsernameNotFoundException(id);
		adressesRepository.delete(adresses);
		
	}

	@Override
	public AdressesDto updateAdresses(AdressesDto adressesDto, String id) {
		
		
		AdressesEntity adressesEntity = adressesRepository.findByIdPublic(id);
		
		if(adressesEntity == null) throw new RuntimeException(id);
		AdressesEntity entity = AdressesMapper.INSTANCE.dtoToEntity(adressesDto);
		adressesEntity.setCity(entity.getCity());
		adressesEntity.setCountry(entity.getCountry());
		adressesEntity.setPostal(entity.getPostal());
		adressesEntity.setStreet(entity.getStreet());
		adressesEntity.setType(entity.getType());
		AdressesDto adresses = AdressesMapper.INSTANCE.entityToDto(adressesEntity);
		adressesRepository.save(adressesEntity);
		return adresses;
	}

	

}
