package com.spring.restaurant.dto;

import java.math.BigDecimal;

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


}
