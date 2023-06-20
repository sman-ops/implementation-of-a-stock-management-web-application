package com.spring.restaurant.services;

import java.util.List;

import com.spring.restaurant.dto.CommandeClientDto;

public interface CommandeClientService {
	
	  CommandeClientDto save(CommandeClientDto dto);

	  CommandeClientDto findById(Integer id);

	  CommandeClientDto findByCode(String code);

	  List<CommandeClientDto> findAll();

	  void delete(Integer id);


}
