package com.spring.restaurant.dto;

import java.time.Instant;
import java.util.List;

import com.spring.restaurant.model.CommandeClient;

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
	  
	  public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
		    if (commandeClient == null) {
		      return null;
		    }
		    return CommandeClientDto.builder()
		        .id(commandeClient.getId())
		        .code(commandeClient.getCode())
		        .dateCommande(commandeClient.getDateCommande())
		        .client(ClientDto.fromEntity(commandeClient.getClient()))
		        .build();

		  }

		  public static CommandeClient toEntity(CommandeClientDto dto) {
		    if (dto == null) {
		      return null;
		    }
		    CommandeClient commandeClient = new CommandeClient();
		    commandeClient.setId(dto.getId());
		    commandeClient.setCode(dto.getCode());
		    commandeClient.setClient(ClientDto.toEntity(dto.getClient()));
		    commandeClient.setDateCommande(dto.getDateCommande());
		    return commandeClient;
		  }

}
