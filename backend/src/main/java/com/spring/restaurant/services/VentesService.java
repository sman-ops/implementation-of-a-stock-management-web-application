package com.spring.restaurant.services;

import java.util.List;

import com.spring.restaurant.dto.VentesDto;


public interface VentesService {
	
	 VentesDto save(VentesDto dto);
	 
	 VentesDto findById(Integer id);

	 VentesDto findByCode(String code);

	 List<VentesDto> findAll();

	 void delete(Integer id);

}
