package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.restaurant.controller.api.ClientApi;
import com.spring.restaurant.dto.ClientDto;
import com.spring.restaurant.services.ClientService;

public class ClientController implements ClientApi{
	
	
	@Autowired
	private ClientService clientService;
	

	@Override
	public ClientDto save(ClientDto dto) {
		
		return clientService.save(dto);
		
	}

	  @Override
	  public ClientDto findById(Integer id) {
	    return clientService.findById(id);
	  }

	  @Override
	  public List<ClientDto> findAll() {
	    return clientService.findAll();
	  }

	  @Override
	  public void delete(Integer id) {
	    clientService.delete(id);
	  }
}
