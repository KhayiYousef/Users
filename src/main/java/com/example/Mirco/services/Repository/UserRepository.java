package com.example.Mirco.services.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Mirco.services.Entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Long>{
	
	UserEntity findByUserId(String id);
	UserEntity findByEmail(String email);

}
