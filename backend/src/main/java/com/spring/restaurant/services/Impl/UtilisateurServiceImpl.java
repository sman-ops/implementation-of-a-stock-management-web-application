package com.spring.restaurant.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restaurant.dto.UtilisateurDto;
import com.spring.restaurant.exception.EntityNotFoundException;
import com.spring.restaurant.exception.ErrorCodes;
import com.spring.restaurant.repository.UtilisateurRepository;
import com.spring.restaurant.services.UtilisateurService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService{
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public UtilisateurDto save(UtilisateurDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UtilisateurDto findById(Integer id) {
		 if (id == null) {
		      log.error("Utilisateur ID is null");
		      return null;
		    }
		    return utilisateurRepository.findById(id)
		        .map(UtilisateurDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucun utilisateur avec l'ID = " + id + " n' ete trouve dans la BDD",
		            ErrorCodes.UTILISATEUR_NOT_FOUND)
		        );
	}

	@Override
	public List<UtilisateurDto> findAll() {
		 return utilisateurRepository.findAll().stream()
			        .map(UtilisateurDto::fromEntity)
			        .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		 if (id == null) {
		      log.error("Utilisateur ID is null");
		      return;
		    }
		    utilisateurRepository.deleteById(id);
		
	}

}
