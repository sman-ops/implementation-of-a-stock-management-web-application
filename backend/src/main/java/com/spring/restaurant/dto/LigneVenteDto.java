package com.spring.restaurant.dto;

import java.math.BigDecimal;

import com.spring.restaurant.model.LigneVente;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneVenteDto {
	
	 private Integer id;
	
	  private VentesDto vente;

	  private ArticleDto article;

	  private BigDecimal quantite;

	  private BigDecimal prixUnitaire;
	  
	  public static LigneVenteDto fromEntity(LigneVente ligneVente) {
		    if (ligneVente == null) {
		      return null;
		    }

		    return LigneVenteDto.builder()
		        .id(ligneVente.getId())
		        .vente(VentesDto.fromEntity(ligneVente.getVente()))
		        .quantite(ligneVente.getQuantite())
		        .prixUnitaire(ligneVente.getPrixUnitaire())
		        .build();
		  }

		  public static LigneVente toEntity(LigneVenteDto dto) {
		    if (dto == null) {
		      return null;
		    }
		    LigneVente ligneVente = new LigneVente();
		    ligneVente.setId(dto.getId());
		    ligneVente.setVente(VentesDto.toEntity(dto.getVente()));
		    ligneVente.setQuantite(dto.getQuantite());
		    ligneVente.setPrixUnitaire(dto.getPrixUnitaire());
		    return ligneVente;
		  }



}
