package com.spring.restaurant.services;

import java.util.List;

import com.spring.restaurant.dto.EntrepriseDto;

public interface EntrepriseService {
	
	EntrepriseDto save(EntrepriseDto dto);

	  EntrepriseDto findById(Integer id);

	  List<EntrepriseDto> findAll();

	  void delete(Integer id);

}
