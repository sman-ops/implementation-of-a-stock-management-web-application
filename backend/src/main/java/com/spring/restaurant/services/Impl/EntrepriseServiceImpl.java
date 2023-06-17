package com.spring.restaurant.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restaurant.dto.EntrepriseDto;
import com.spring.restaurant.exception.EntityNotFoundException;
import com.spring.restaurant.exception.ErrorCodes;
import com.spring.restaurant.exception.InvalidEntityException;
import com.spring.restaurant.repository.EntrepriseRepository;
import com.spring.restaurant.services.EntrepriseService;
import com.spring.restaurant.validator.EntrepriseValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Override
	public EntrepriseDto save(EntrepriseDto dto) {
		 List<String> errors = EntrepriseValidator.validate(dto);
		    if (!errors.isEmpty()) {
		      log.error("Entreprise is not valid {}", dto);
		      throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
		    }
		    EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
		        entrepriseRepository.save(EntrepriseDto.toEntity(dto))
		    );
		    return savedEntreprise;
	}

	@Override
	public EntrepriseDto findById(Integer id) {
		 if (id == null) {
		      log.error("Entreprise ID is null");
		      return null;
		    }
		    return entrepriseRepository.findById(id)
		        .map(EntrepriseDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD",
		            ErrorCodes.ENTREPRISE_NOT_FOUND)
		        );
	}

	@Override
	public List<EntrepriseDto> findAll() {
		 return entrepriseRepository.findAll().stream()
			        .map(EntrepriseDto::fromEntity)
			        .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		 if (id == null) {
		      log.error("Entreprise ID is null");
		      return;
		    }
		    entrepriseRepository.deleteById(id);
		  }
		
	}


