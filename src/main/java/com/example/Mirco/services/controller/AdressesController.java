package com.example.Mirco.services.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Mirco.services.Dto.AdressesDto;
import com.example.Mirco.services.Request.AdressesRequest;
import com.example.Mirco.services.Response.AdressesResponse;
import com.example.Mirco.services.StructMapper.AdressesMapper;
import com.example.Mirco.services.service.AdressesServices;

@RequestMapping("/adresses")
@RestController
@CrossOrigin("*")
public class AdressesController {
	
	@Autowired
	AdressesServices adressesServices;
	@PostMapping
	public ResponseEntity<AdressesResponse> saveAdresses(@RequestBody AdressesRequest adressesRequest,Principal principal){
		AdressesDto adressesDto = AdressesMapper.INSTANCE.requestToDto(adressesRequest);
		AdressesDto dtoAdresses = adressesServices.addAdresses(adressesDto,principal.getName());
		AdressesResponse adressesResponse = AdressesMapper.INSTANCE.dtoToResponse(dtoAdresses);
		return new ResponseEntity<AdressesResponse>(adressesResponse,HttpStatus.CREATED);
	}
	
	@PostMapping("add")
	public ResponseEntity<AdressesResponse> saveAddress(@RequestBody AdressesRequest adressesRequest){
		AdressesDto adressesDto = AdressesMapper.INSTANCE.requestToDto(adressesRequest);
		AdressesDto dtoAdresses = adressesServices.addAdresses(adressesDto);
		AdressesResponse adressesResponse = AdressesMapper.INSTANCE.dtoToResponse(dtoAdresses);
		return new ResponseEntity<AdressesResponse>(adressesResponse,HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<AdressesResponse> updateAdresses(@PathVariable String id,@RequestBody AdressesRequest adressesRequest,Principal principal)
	{
		AdressesDto adressesDto = AdressesMapper.INSTANCE.requestToDto(adressesRequest);
		AdressesDto dtosAdresses = adressesServices.updateAdresses(adressesDto, id,principal.getName());
		AdressesResponse adressesResponse = AdressesMapper.INSTANCE.dtoToResponse(dtosAdresses);
		return new ResponseEntity<AdressesResponse>(adressesResponse,HttpStatus.ACCEPTED);
	}
	@PutMapping("all/{id}")
	public ResponseEntity<AdressesResponse> updateAdressess(@PathVariable String id,@RequestBody AdressesRequest adressesRequest)
	{
		AdressesDto adressesDto = AdressesMapper.INSTANCE.requestToDto(adressesRequest);
		AdressesDto dtosAdresses = adressesServices.updateAdresses(adressesDto, id);
		AdressesResponse adressesResponse = AdressesMapper.INSTANCE.dtoToResponse(dtosAdresses);
		return new ResponseEntity<AdressesResponse>(adressesResponse,HttpStatus.ACCEPTED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<AdressesResponse> adressesById(@PathVariable String id) {
		AdressesDto adressesDto = adressesServices.adressesById(id);
		AdressesResponse adressesResponse = AdressesMapper.INSTANCE.dtoToResponse(adressesDto);
		return new ResponseEntity<AdressesResponse>(adressesResponse,HttpStatus.OK);
	}
	@GetMapping()
	public ResponseEntity<List<AdressesResponse>> allAdresses(Principal principal){
		List<AdressesResponse> adressesResponses = new ArrayList<>();
		List<AdressesDto> adressesDtos = adressesServices.allAdresses(principal.getName());
		adressesResponses = adressesDtos.stream()
				.map((AdressesDto adresses)->(
						new AdressesResponse(adresses.getIdPublic(),adresses.getCity(),adresses.getCountry(),adresses.getStreet(),adresses.getPostal(),adresses.getType())
						)).toList();
		return new ResponseEntity<List<AdressesResponse>>(adressesResponses,HttpStatus.OK);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<AdressesResponse>> allAdresses(){
		List<AdressesResponse> adressesResponses = new ArrayList<>();
		List<AdressesDto> adressesDtos = adressesServices.allAdresses();
		adressesResponses = adressesDtos.stream()
				.map((AdressesDto adresses)->(
						new AdressesResponse(adresses.getIdPublic(),adresses.getCity(),adresses.getCountry(),adresses.getStreet(),adresses.getPostal(),adresses.getType())
						)).toList();
		return new ResponseEntity<List<AdressesResponse>>(adressesResponses,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> removeAdresses(@PathVariable String id){
		adressesServices.deleteAdresses(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
