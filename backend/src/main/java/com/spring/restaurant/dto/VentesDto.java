package com.spring.restaurant.dto;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VentesDto {
	
	 private Integer id;
	
	  private String code;

	  private Instant dateVente;

	  private String commentaire;

	  private List<LigneVenteDto> ligneVentes;

}
