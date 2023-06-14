package com.spring.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer>{
	
	Optional<Article> findArticleByCodeArticle(String codeArticle);

}
