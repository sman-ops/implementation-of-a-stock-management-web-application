package com.spring.restaurant.services;

import java.util.List;

import com.spring.restaurant.dto.CategoryDto;

public interface CategoryService {
	
	  CategoryDto save(CategoryDto dto);

	  CategoryDto findById(Integer id);

	  CategoryDto findByCode(String code);

	  List<CategoryDto> findAll();

	  void delete(Integer id);

}
