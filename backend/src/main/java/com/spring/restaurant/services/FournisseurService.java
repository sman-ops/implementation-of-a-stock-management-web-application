package com.spring.restaurant.services;

import java.util.List;

import com.spring.restaurant.dto.FournisseurDto;

public interface FournisseurService {
	
	  FournisseurDto save(FournisseurDto dto);

	  FournisseurDto findById(Integer id);

	  List<FournisseurDto> findAll();

	  void delete(Integer id);

}
