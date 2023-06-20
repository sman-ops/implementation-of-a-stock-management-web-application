package com.spring.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.CommandeClient;

@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
	
	Optional<CommandeClient> findCommandeClientByCode(String code);

}
