package com.spring.restaurant.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.spring.restaurant.utils.Constants.APP_ROOT;

import java.util.List;

import com.spring.restaurant.dto.CategoryDto;

public interface CategoryApi {
	
	  @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  CategoryDto save(@RequestBody CategoryDto dto);

	  @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	  CategoryDto findById(@PathVariable("idCategory") Integer idCategory);

	  @GetMapping(value = APP_ROOT + "/categories/filter/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	  CategoryDto findByCode(@PathVariable("codeCategory") String codeCategory);

	  @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
	  List<CategoryDto> findAll();

	  @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
	  void delete(@PathVariable("idCategory") Integer id);

}
