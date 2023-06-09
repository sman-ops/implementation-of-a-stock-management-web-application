package com.spring.restaurant.dto;

import java.math.BigDecimal;

import com.spring.restaurant.model.CommandeFournisseur;

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

}
