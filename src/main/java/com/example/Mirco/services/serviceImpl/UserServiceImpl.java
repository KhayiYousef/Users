package com.example.Mirco.services.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Mirco.services.Utils;
import com.example.Mirco.services.Dto.AdressesDto;
import com.example.Mirco.services.Dto.UserDto;
import com.example.Mirco.services.Entity.AdressesEntity;
import com.example.Mirco.services.Entity.UserEntity;
import com.example.Mirco.services.Enumation.Gender;
import com.example.Mirco.services.Repository.UserRepository;
import com.example.Mirco.services.StructMapper.UserMapper;
import com.example.Mirco.services.service.UserServices;

@Service
public class UserServiceImpl implements UserServices{
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		if(userRepository.findByEmail(userDto.getEmail()) != null) throw new RuntimeException("email deja exist");
		UserEntity userEntity = UserMapper.INSTANCE.dtoToEntity(userDto);
		for(int i=0;i<userEntity.getAdresses().size();i++)
		{
			userEntity.getAdresses().get(i).setIdPublic(utils.generatedUserId(10));
			userEntity.getAdresses().get(i).setUserEntity(userEntity);;
		}
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setUserId(utils.generatedUserId(23));
		UserEntity userCreated = userRepository.save(userEntity);
		UserDto dto = UserMapper.INSTANCE.entityToDto(userCreated);
		return dto;
		
	}

	@Override
	public UserDto userById(String id) {
		UserEntity userEntity =  userRepository.findByUserId(id);
		if(userEntity == null ) throw new UsernameNotFoundException(id);
		return UserMapper.INSTANCE.entityToDto(userEntity);
	}

	@Override
	public UserDto updateUser(UserDto userDto, String id) {
		UserEntity userEntity =  userRepository.findByUserId(id);
		if(userEntity == null ) throw new UsernameNotFoundException(id);
		if(userDto.getEmail()!=null)
				userEntity.setEmail(userDto.getEmail());
		if(userDto.getFname()!=null) 
			userEntity.setFname(userDto.getFname());
		if(userDto.getLname()!=null)
			userEntity.setLname(userDto.getLname());
		UserDto dto = UserMapper.INSTANCE.entityToDto(userEntity);
		userRepository.save(userEntity);
		return dto;
	}

	@Override
	public void deleteUser(String id) {
		UserEntity userEntity =  userRepository.findByUserId(id);
		if(userEntity == null ) throw new UsernameNotFoundException(id);
		userRepository.delete(userEntity);
	}

	@Override
	public List<UserDto> AllUsers() {
		List<UserEntity> userEntities =  (List<UserEntity>) userRepository.findAll();
		List<UserDto> dtos2 = userEntities.stream()
		.map((UserEntity user) -> (
				new UserDto(user.getId(),user.getUserId(),user.getFname(),user.getLname(),user.getGender(),user.getEmail(),null,user.getEncryptedPassword() ,user.getAdresses().stream()
						.map((AdressesEntity ad) -> (new AdressesDto(ad.getId(),ad.getIdPublic(),ad.getCity(),ad.getCountry(),ad.getStreet(),ad.getPostal(),ad.getType())
						)).collect(Collectors.toList())
						))).toList();
		return dtos2;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity==null)throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public List<UserDto> femaleUsers() {
		List<UserEntity> userEntities =  (List<UserEntity>) userRepository.findAll();
		List<UserEntity> userEntitiesFemale =userEntities.stream()
				.filter(user -> user.getGender().equals(Gender.FEMALE))
				.collect(Collectors.toList());
		List<UserDto> dtos = userEntitiesFemale.stream()
				.map((UserEntity user)->(
						new UserDto(user.getId(),user.getUserId(),user.getFname(),user.getLname(),user.getGender(),user.getEmail(),null,user.getEncryptedPassword() ,user.getAdresses().stream()
								.map((AdressesEntity ad) -> (new AdressesDto(ad.getId(),ad.getIdPublic(),ad.getCity(),ad.getCountry(),ad.getStreet(),ad.getPostal(),ad.getType())
										)).collect(Collectors.toList()))
						)).toList();
		UserEntity cgi = userEntities.stream()
				.reduce((user1,user2)->user1.getEmail().length()>user2.getEmail().length()?
						user1:user2).get();
		System.out.println(cgi);
		return dtos;
	}

	@Override
	public UserDto userByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity==null)throw new UsernameNotFoundException(email);
		UserDto dto = UserMapper.INSTANCE.entityToDto(userEntity);
		return dto;
	}

}
