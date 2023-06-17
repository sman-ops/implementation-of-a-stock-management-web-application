package com.spring.restaurant.services;

import java.util.List;

import com.spring.restaurant.dto.ClientDto;

public interface ClientService {
	
	  ClientDto save(ClientDto dto);

	  ClientDto findById(Integer id);

	  List<ClientDto> findAll();

	  void delete(Integer id);

}
