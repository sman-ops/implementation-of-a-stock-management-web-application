package com.spring.restaurant.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.spring.restaurant.model.MvtStk;
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
	  
	  public static MvtStkDto fromEntity(MvtStk mvtStk) {
		    if (mvtStk == null) {
		      return null;
		    }

		    return MvtStkDto.builder()
		        .id(mvtStk.getId())
		        .dateMvt(mvtStk.getDateMvt())
		        .quantite(mvtStk.getQuantite())
		        .article(ArticleDto.fromEntity(mvtStk.getArticle()))
		        .typeMvt(mvtStk.getTypeMvt())
		        .build();
		  }

		  public static MvtStk toEntity(MvtStkDto dto) {
		    if (dto == null) {
		      return null;
		    }

		    MvtStk mvtStk = new MvtStk();
		    mvtStk.setId(dto.getId());
		    mvtStk.setDateMvt(dto.getDateMvt());
		    mvtStk.setQuantite(dto.getQuantite());
		    mvtStk.setArticle(ArticleDto.toEntity(dto.getArticle()));
		    mvtStk.setTypeMvt(dto.getTypeMvt());
		    return mvtStk;
		  }

	


}
