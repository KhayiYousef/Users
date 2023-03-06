package com.example.Mirco.services.mapping;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Mirco.services.Dto.UserDto;
import com.example.Mirco.services.Entity.AdressesEntity;
import com.example.Mirco.services.Entity.UserEntity;
import com.example.Mirco.services.Enumation.Gender;
import com.example.Mirco.services.StructMapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Test @Order(1)
	public void entityToDto() {
		UserEntity userEntity = new UserEntity();
		userEntity.setFname("yousef");
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode("123456"));
		userEntity.setEmail("yousef@cgi.com");
		userEntity.setGender(Gender.MALE);
		userEntity.setLname("khayi");
		List<AdressesEntity> adressesEntities = new ArrayList<>();
		userEntity.setAdresses(adressesEntities);
		UserDto userDto = UserMapper.INSTANCE.entityToDto(userEntity);
		
		Assertions.assertNotNull(userDto);
		Assertions.assertEquals(userEntity.getEmail(), userDto.getEmail());
		Assertions.assertEquals(userEntity.getEncryptedPassword(), userDto.getEncryptedPassword());
	}
	
	@Test @Order(2)
	public void dtoToEntity() {
		UserDto userDto = new UserDto();
		userDto.setEmail("you@cgi.com");
		userDto.setFname("yousef");
		userDto.setLname("khayi");
		userDto.setAdresses(new ArrayList<>());
		userDto.setGender(Gender.MALE);
		userDto.setEncryptedPassword(bCryptPasswordEncoder.encode("123456"));
		UserEntity userEntity = UserMapper.INSTANCE.dtoToEntity(userDto);
		Assertions.assertNotNull(userDto);
		Assertions.assertEquals(userEntity.getEmail(), userDto.getEmail());
		Assertions.assertEquals(userEntity.getEncryptedPassword(), userDto.getEncryptedPassword());
	}

}
