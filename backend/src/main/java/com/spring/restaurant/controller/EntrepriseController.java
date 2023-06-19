package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.restaurant.controller.api.EntrepriseApi;
import com.spring.restaurant.dto.EntrepriseDto;
import com.spring.restaurant.services.EntrepriseService;

public class EntrepriseController implements EntrepriseApi {
	
	@Autowired
	private EntrepriseService  entrepriseService;
	

	@Override
	public EntrepriseDto save(EntrepriseDto dto) {
	return entrepriseService.save(dto);
	}

	  @Override
	  public EntrepriseDto findById(Integer id) {
	    return entrepriseService.findById(id);
	  }

	  @Override
	  public List<EntrepriseDto> findAll() {
	    return entrepriseService.findAll();
	  }

	  @Override
	  public void delete(Integer id) {
	    entrepriseService.delete(id);
	  }

}
