package com.spring.restaurant.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.spring.restaurant.model.TypeMvtStk;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MvtStkDto {
	
	 private Integer id;
	
	 private Instant dateMvt;

	  private BigDecimal quantite;

	  private ArticleDto article;

	  private TypeMvtStk typeMvt;

	


}
