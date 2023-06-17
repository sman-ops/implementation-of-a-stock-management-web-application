package com.spring.restaurant.services;

import java.util.List;

import com.spring.restaurant.dto.UtilisateurDto;

public interface UtilisateurService {
	
	  UtilisateurDto save(UtilisateurDto dto);

	  UtilisateurDto findById(Integer id);

	  List<UtilisateurDto> findAll();

	  void delete(Integer id);


}
