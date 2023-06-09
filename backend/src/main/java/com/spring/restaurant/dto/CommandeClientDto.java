package com.spring.restaurant.dto;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommandeClientDto {
	
	 private Integer id;
	
	  private String code;

	  private Instant dateCommande;


	  private ClientDto client;

	  private Integer idEntreprise;

	  private List<LigneCommandeClientDto> ligneCommandeClients;

}
