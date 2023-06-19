package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.restaurant.controller.api.UtilisateurApi;
import com.spring.restaurant.dto.UtilisateurDto;
import com.spring.restaurant.services.UtilisateurService;

public class UtilisateurController implements UtilisateurApi {

	 private UtilisateurService utilisateurService;

	  @Autowired
	  public UtilisateurController(UtilisateurService utilisateurService) {
	    this.utilisateurService = utilisateurService;
	  }

	  @Override
	  public UtilisateurDto save(UtilisateurDto dto) {
	    return utilisateurService.save(dto);
	  }


	  @Override
	  public UtilisateurDto findById(Integer id) {
	    return utilisateurService.findById(id);
	  }

	

	  @Override
	  public List<UtilisateurDto> findAll() {
	    return utilisateurService.findAll();
	  }

	  @Override
	  public void delete(Integer id) {
	    utilisateurService.delete(id);
	  }

}
