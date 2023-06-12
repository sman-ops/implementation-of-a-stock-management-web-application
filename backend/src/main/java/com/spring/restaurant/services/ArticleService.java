package com.spring.restaurant.services;

import java.util.List;

import com.spring.restaurant.dto.ArticleDto;

public interface ArticleService {
	
	  ArticleDto save(ArticleDto dto);

	  ArticleDto findById(Integer id);

	  ArticleDto findByCodeArticle(String codeArticle);

	  List<ArticleDto> findAll();


	  List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);

	  void delete(Integer id);

}
