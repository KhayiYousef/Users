package com.example.Mirco.services.StructMapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.example.Mirco.services.Dto.AdressesDto;
import com.example.Mirco.services.Dto.UserDto;
import com.example.Mirco.services.Entity.AdressesEntity;
import com.example.Mirco.services.Entity.UserEntity;
import com.example.Mirco.services.Request.AdressesRequest;
import com.example.Mirco.services.Request.UserRequest;
import com.example.Mirco.services.Response.AdressesResponse;
import com.example.Mirco.services.Response.UserResponse;

@Mapper
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper( UserMapper.class ); 
	 
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "fname", target = "fname")
    @Mapping(source = "lname", target = "lname")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "encryptedPassword", target = "encryptedPassword")
    @Mapping(source = "adresses", target = "adresses", qualifiedByName = "mapAddressesEntityToDto")
    UserDto entityToDto(UserEntity userEntity); 
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "fname", target = "fname")
    @Mapping(source = "lname", target = "lname")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "encryptedPassword", target = "encryptedPassword")
    @Mapping(source = "adresses", target = "adresses", qualifiedByName = "mapAddressesDtoToEntity")
    UserEntity dtoToEntity(UserDto userDto);
    
    
    UserResponse dtoToResponse(UserDto userDto);
    UserDto requestToDto(UserRequest userRequest);
    
    @Named("mapAddressesDtoToEntity")
    default List<AdressesEntity> mapAddressesDtoToEntity(List<AdressesDto> addressesDto) {
        return addressesDto.stream()
        		.map((AdressesDto adresses)->(
						new AdressesEntity(adresses.getId(),adresses.getIdPublic(),adresses.getCity(),adresses.getCountry(),adresses.getStreet(),adresses.getPostal(),adresses.getType())
						)).toList();
    }
    
    @Named("mapAddressesEntityToDto")
    default List<AdressesDto> mapAddressesEntityToDto(List<AdressesEntity> addressesEntities) {
        return addressesEntities.stream()
        		.map((AdressesEntity adresses)->(
						new AdressesDto(adresses.getId(),adresses.getIdPublic(),adresses.getCity(),adresses.getCountry(),adresses.getStreet(),adresses.getPostal(),adresses.getType())
						)).toList();
    }
	
}
