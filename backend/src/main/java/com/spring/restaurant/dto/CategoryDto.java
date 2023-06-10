package com.spring.restaurant.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.restaurant.model.Category;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryDto {
	
	  private Integer id;
	
	  private String code;

	  private String designation;

	  private Integer idEntreprise;
	  
	  @JsonIgnore
	  private List<ArticleDto> articles;
	  
	
	  public static CategoryDto fromEntity(Category category) {
		  
		  if(category == null) {
			  return null;
			  //TODO throw anexception 
			  
		  }
		  
		  // Mapping between categroy -> categoryDto
		return CategoryDto.builder()
				    .id(category.getId())
			        .code(category.getCode())
			        .designation(category.getDesignation())
			        .idEntreprise(category.getIdEntreprise())
			        .build();
		  
	  }
	  
 public static Category toEntity(CategoryDto categoryDto) {
		  
		  if(categoryDto == null) {
			  return null;
			  //TODO throw anexception 
			  
		  }
		  
		  // Mapping between categroyDto -> category
		   Category category = new Category();
		    category.setId(categoryDto.getId());
		    category.setCode(categoryDto.getCode());
		    category.setDesignation(categoryDto.getDesignation());
		    category.setIdEntreprise(categoryDto.getIdEntreprise());
		    return category;
		
		  
	  }
 
 


}
