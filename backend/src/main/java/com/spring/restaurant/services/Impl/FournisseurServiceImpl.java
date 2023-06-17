package com.spring.restaurant.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restaurant.dto.FournisseurDto;
import com.spring.restaurant.exception.EntityNotFoundException;
import com.spring.restaurant.exception.ErrorCodes;
import com.spring.restaurant.exception.InvalidEntityException;
import com.spring.restaurant.repository.FournisseurRepository;
import com.spring.restaurant.services.FournisseurService;
import com.spring.restaurant.validator.FournisseurValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {
	
	
	@Autowired
	private FournisseurRepository fournisseurRepository;

	@Override
	public FournisseurDto save(FournisseurDto dto) {
	    List<String> errors = FournisseurValidator.validate(dto);
	    if (!errors.isEmpty()) {
	      log.error("Fournisseur is not valid {}", dto);
	      throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
	    }

	    return FournisseurDto.fromEntity(
	        fournisseurRepository.save(
	            FournisseurDto.toEntity(dto)
	        )
	    );

	}

	@Override
	public FournisseurDto findById(Integer id) {
		 if (id == null) {
		      log.error("Fournisseur ID is null");
		      return null;
		    }
		    return fournisseurRepository.findById(id)
		        .map(FournisseurDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucun fournisseur avec l'ID = " + id + " n' ete trouve dans la BDD",
		            ErrorCodes.FOURNISSEUR_NOT_FOUND)
		        );
	}

	@Override
	public List<FournisseurDto> findAll() {
		 return fournisseurRepository.findAll().stream()
			        .map(FournisseurDto::fromEntity)
			        .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
		      log.error("Fournisseur ID is null");
		      return;
		    }
		
		 fournisseurRepository.deleteById(id);
		
	}

}
