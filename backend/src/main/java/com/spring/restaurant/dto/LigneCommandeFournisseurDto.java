package com.spring.restaurant.dto;

import java.math.BigDecimal;

import com.spring.restaurant.model.CommandeFournisseur;
import com.spring.restaurant.model.LigneCommandeFournisseur;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeFournisseurDto {
	
	 private Integer id;
	
	  private ArticleDto article;

	  private CommandeFournisseur commandeFournisseur;

	  private BigDecimal quantite;

	  private BigDecimal prixUnitaire;
	  
	  public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
		    if (ligneCommandeFournisseur == null) {
		      return null;
		    }
		    return LigneCommandeFournisseurDto.builder()
		        .id(ligneCommandeFournisseur.getId())
		        .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
		        .quantite(ligneCommandeFournisseur.getQuantite())
		        .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
		        .build();
		  }

		  public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto dto) {
		    if (dto == null) {
		      return null;
		    }

		    LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
		    ligneCommandeFournisseur.setId(dto.getId());
		    ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(dto.getArticle()));
		    ligneCommandeFournisseur.setPrixUnitaire(dto.getPrixUnitaire());
		    ligneCommandeFournisseur.setQuantite(dto.getQuantite());
		    return ligneCommandeFournisseur;
		  }

}
