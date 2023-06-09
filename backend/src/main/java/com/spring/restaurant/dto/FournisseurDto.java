package com.spring.restaurant.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FournisseurDto {
	
	 private Integer id;
	
	  private String nom;

	  private String prenom;

	  private AdresseDto adresse;

	  private String photo;

	  private String mail;

	  private String numTel;
	  
	  private List<CommandeFournisseurDto> commandeFournisseurs;

}
