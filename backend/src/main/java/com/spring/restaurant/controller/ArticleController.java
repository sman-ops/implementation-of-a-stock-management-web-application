package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.controller.api.ArticleApi;
import com.spring.restaurant.dto.ArticleDto;
import com.spring.restaurant.services.ArticleService;


@RestController
public class ArticleController implements ArticleApi {
	
	// Fieled Injection  ( constructor and setters injection )
	@Autowired
	private ArticleService articleService;

	@Override
	public ArticleDto save(ArticleDto dto) {
		
	return 	articleService.save(dto);
		
	}

	@Override
	public ArticleDto findById(Integer id) {
		return articleService.findById(id);
	}

	@Override
	public ArticleDto findByCodeArticle(String codeArticle) {
		return articleService.findByCodeArticle(codeArticle);
	}

	@Override
	public List<ArticleDto> findAll() {
		return articleService.findAll();
		}

	@Override
	public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		articleService.delete(id);
		
	}

}
