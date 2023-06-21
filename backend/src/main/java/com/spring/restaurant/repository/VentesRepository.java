package com.spring.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.Ventes;

@Repository
public interface VentesRepository extends JpaRepository<Ventes, Integer> {
	
	 Optional<Ventes> findVentesByCode(String code);

}
