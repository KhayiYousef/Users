package com.example.Mirco.services.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Mirco.services.Entity.AdressesEntity;

public interface AdressesRepository extends JpaRepository<AdressesEntity, Long> {
	
	AdressesEntity findByIdPublic(String id);

}
