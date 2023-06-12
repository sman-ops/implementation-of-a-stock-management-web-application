package com.spring.restaurant.services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.restaurant.dto.ArticleDto;
import com.spring.restaurant.services.ArticleService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

	@Override
	public ArticleDto save(ArticleDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleDto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleDto findByCodeArticle(String codeArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
