package com.spring.restaurant.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntrepriseDto {
	
	 private Integer id;
	
	  private String nom;

	  private String description;

	  private AdresseDto adresse;

	  private String codeFiscal;

	  private String photo;

	  private String email;

	  private String numTel;

	  private String steWeb;


	  private List<UtilisateurDto> utilisateurs;


}
