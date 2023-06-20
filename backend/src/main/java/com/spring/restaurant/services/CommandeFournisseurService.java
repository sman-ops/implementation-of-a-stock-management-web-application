package com.spring.restaurant.services;

import java.util.List;

import com.spring.restaurant.dto.CommandeFournisseurDto;

public interface CommandeFournisseurService {
	
	 CommandeFournisseurDto save(CommandeFournisseurDto dto);
	 
	  CommandeFournisseurDto findById(Integer id);

	  CommandeFournisseurDto findByCode(String code);

	  List<CommandeFournisseurDto> findAll();

	  void delete(Integer id);

}
