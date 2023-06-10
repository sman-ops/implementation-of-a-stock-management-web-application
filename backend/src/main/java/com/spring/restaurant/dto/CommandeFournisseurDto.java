package com.spring.restaurant.dto;

import java.time.Instant;
import java.util.List;

import com.spring.restaurant.model.CommandeFournisseur;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommandeFournisseurDto {
	
	 private Integer id;
	
	 private String code;

	  private Instant dateCommande;

	  private FournisseurDto fournisseur;

	  private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;
	  
	  public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
		    if (commandeFournisseur == null) {
		      return null;
		    }
		    return CommandeFournisseurDto.builder()
		        .id(commandeFournisseur.getId())
		        .code(commandeFournisseur.getCode())
		        .dateCommande(commandeFournisseur.getDateCommande())
		        .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
		        .build();
		  }

		  public static CommandeFournisseur toEntity(CommandeFournisseurDto dto) {
		    if (dto == null) {
		      return null;
		    }
		    CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
		    commandeFournisseur.setId(dto.getId());
		    commandeFournisseur.setCode(dto.getCode());
		    commandeFournisseur.setDateCommande(dto.getDateCommande());
		    commandeFournisseur.setFournisseur(FournisseurDto.toEntity(dto.getFournisseur()));
		 
		    return commandeFournisseur;
		  }

}
