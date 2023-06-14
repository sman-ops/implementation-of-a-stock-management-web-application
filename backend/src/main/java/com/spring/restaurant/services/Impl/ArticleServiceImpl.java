package com.spring.restaurant.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.restaurant.dto.ArticleDto;
import com.spring.restaurant.exception.EntityNotFoundException;
import com.spring.restaurant.exception.ErrorCodes;
import com.spring.restaurant.exception.InvalidEntityException;
import com.spring.restaurant.model.Article;
import com.spring.restaurant.repository.ArticleRepository;
import com.spring.restaurant.services.ArticleService;
import com.spring.restaurant.validator.ArticleValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	

	@Override
	public ArticleDto save(ArticleDto dto) {
		List<String> errors = ArticleValidator.validate(dto);
		if(!errors.isEmpty()) {
			log.error("Article  is not valid {}",dto);
			throw new InvalidEntityException("Article n'est pas valide",ErrorCodes.ARTICLE_NOT_VALID,errors);
		}
		
		// Article  savedArticle = articleRepository.save(articleDto.toEntity(dto));
		
		 return ArticleDto.fromEntity(
			        articleRepository.save(
			            ArticleDto.toEntity(dto)
			        )
			    );
			  }

	@Override
	public ArticleDto findById(Integer id) {
		
		if(id == null) {
			log.error("Article ID is null ");
			return null;
		}
			
		Optional<Article> article = articleRepository.findById(id);
		
		return  Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->
		 new EntityNotFoundException(
		            "Aucun article avec l'ID = " + id + " n' ete trouve dans la BDD",
		            ErrorCodes.ARTICLE_NOT_FOUND)
		);
	}

	@Override
	public ArticleDto findByCodeArticle(String codeArticle) {
		 if (!StringUtils.hasLength(codeArticle)) {
		      log.error("Article CODE is null");
		      return null;
		    }
		 
		 Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);
			
			return  Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->
			 new EntityNotFoundException(
			            "Aucun article avec le code = " + codeArticle + " n' ete trouve dans la BDD",
			            ErrorCodes.ARTICLE_NOT_FOUND)
			);

	}

	@Override
	public List<ArticleDto> findAll() {
		
		 return articleRepository.findAll().stream()
			        .map(ArticleDto::fromEntity)
			        .collect(Collectors.toList());
	}

	@Override
	public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		  if (id == null) {
		      log.error("Article ID is null");
		   
		      return;
		    }
		  
		  articleRepository.deleteById(id);
		
	}

}
