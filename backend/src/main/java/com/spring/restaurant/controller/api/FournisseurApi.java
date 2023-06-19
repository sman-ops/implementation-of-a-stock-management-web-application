package com.spring.restaurant.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.spring.restaurant.utils.Constants.FOURNISSEUR_ENDPOINT;
import com.spring.restaurant.dto.FournisseurDto;

public interface FournisseurApi {
	
	  @PostMapping(FOURNISSEUR_ENDPOINT + "/create")
	  FournisseurDto save(@RequestBody FournisseurDto dto);

	  @GetMapping(FOURNISSEUR_ENDPOINT + "/{idFournisseur}")
	  FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

	  @GetMapping(FOURNISSEUR_ENDPOINT + "/all")
	  List<FournisseurDto> findAll();

	  @DeleteMapping(FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}")
	  void delete(@PathVariable("idFournisseur") Integer id);

}
