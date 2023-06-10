package com.spring.restaurant.validator;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import com.spring.restaurant.dto.CategoryDto;

public class CategoryValidator {
	
	 public static List<String> validate(CategoryDto categoryDto) {
		    List<String> errors = new ArrayList<>();

		    if (categoryDto == null || !StringUtils.hasLength(categoryDto.getCode())) {
		      errors.add("Veuillez renseigner le code de la categorie");
		    }
		    return errors;
		  }

}
