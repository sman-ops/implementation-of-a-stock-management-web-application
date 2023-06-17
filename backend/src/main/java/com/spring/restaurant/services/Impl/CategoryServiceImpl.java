package com.spring.restaurant.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restaurant.dto.CategoryDto;
import com.spring.restaurant.exception.EntityNotFoundException;
import com.spring.restaurant.exception.ErrorCodes;
import com.spring.restaurant.exception.InvalidEntityException;
import com.spring.restaurant.repository.CategoryRepository;
import com.spring.restaurant.services.CategoryService;
import com.spring.restaurant.validator.CategoryValidator;

import org.springframework.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public CategoryDto save(CategoryDto dto) {
		
	    List<String> errors = CategoryValidator.validate(dto);
	    if (!errors.isEmpty()) {
	      log.error("Article is not valid {}", dto);
	      throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
	    }
	    return CategoryDto.fromEntity(
	        categoryRepository.save(CategoryDto.toEntity(dto))
	    );

		
	}

	@Override
	public CategoryDto findById(Integer id) {
		  if (id == null) {
		      log.error("Category ID is null");
		      return null;
		    }
		    return categoryRepository.findById(id)
		        .map(CategoryDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune category avec l'ID = " + id + " n' ete trouve dans la BDD",
		            ErrorCodes.CATEGORY_NOT_FOUND)
		        );
	}

	@Override
	public CategoryDto findByCode(String code) {
		 if (!StringUtils.hasLength(code)) {
		      log.error("Category CODE is null");
		      return null;
		    }
		    return categoryRepository.findCategoryByCode(code)
		        .map(CategoryDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune category avec le CODE = " + code + " n' ete trouve dans la BDD",
		            ErrorCodes.CATEGORY_NOT_FOUND)
		        );
	}

	@Override
	public List<CategoryDto> findAll() {
		 return categoryRepository.findAll().stream()
			        .map(CategoryDto::fromEntity)
			        .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		  if (id == null) {
		      log.error("Category ID is null");
		      return;
		    }
		   
		    categoryRepository.deleteById(id);
		  }
		
	

}
