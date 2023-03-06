package com.example.Mirco.services.StructMapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.Mirco.services.Dto.AdressesDto;
import com.example.Mirco.services.Entity.AdressesEntity;
import com.example.Mirco.services.Request.AdressesRequest;
import com.example.Mirco.services.Response.AdressesResponse;
@Mapper
public interface AdressesMapper {

	AdressesMapper INSTANCE = Mappers.getMapper( AdressesMapper.class ); 
	AdressesDto entityToDto(AdressesEntity adressesEntity);
    
    AdressesEntity dtoToEntity(AdressesDto adressesDto);
    AdressesDto requestToDto(AdressesRequest adressesRequest);
    AdressesResponse dtoToResponse(AdressesDto adressesDto);
}
