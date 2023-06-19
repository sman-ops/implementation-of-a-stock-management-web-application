package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.restaurant.controller.api.FournisseurApi;
import com.spring.restaurant.dto.FournisseurDto;
import com.spring.restaurant.services.FournisseurService;

public class FournisseurController implements FournisseurApi {

	 private FournisseurService fournisseurService;

	  @Autowired
	  public FournisseurController(FournisseurService fournisseurService) {
	    this.fournisseurService = fournisseurService;
	  }

	  @Override
	  public FournisseurDto save(FournisseurDto dto) {
	    return fournisseurService.save(dto);
	  }

	  @Override
	  public FournisseurDto findById(Integer id) {
	    return fournisseurService.findById(id);
	  }

	  @Override
	  public List<FournisseurDto> findAll() {
	    return fournisseurService.findAll();
	  }

	  @Override
	  public void delete(Integer id) {
	    fournisseurService.delete(id);
	  }
}
