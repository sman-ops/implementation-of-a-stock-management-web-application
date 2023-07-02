package com.spring.restaurant.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.restaurant.dto.LigneVenteDto;
import com.spring.restaurant.dto.VentesDto;
import com.spring.restaurant.exception.EntityNotFoundException;
import com.spring.restaurant.exception.ErrorCodes;
import com.spring.restaurant.exception.InvalidEntityException;
import com.spring.restaurant.model.Article;
import com.spring.restaurant.model.LigneVente;
import com.spring.restaurant.model.Ventes;
import com.spring.restaurant.repository.ArticleRepository;
import com.spring.restaurant.repository.LigneVenteRepository;
import com.spring.restaurant.repository.VentesRepository;
import com.spring.restaurant.services.VentesService;
import com.spring.restaurant.validator.VentesValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class VentesServiceImpl implements VentesService {
	
	  private ArticleRepository articleRepository;
	  private VentesRepository ventesRepository;
	  private LigneVenteRepository ligneVenteRepository;
	 

	  @Autowired
	  public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository,
	      LigneVenteRepository ligneVenteRepository) {
	    this.articleRepository = articleRepository;
	    this.ventesRepository = ventesRepository;
	    this.ligneVenteRepository = ligneVenteRepository;
	    
	  }

	@Override
	public VentesDto save(VentesDto dto) {
		
		   List<String> errors = VentesValidator.validate(dto);
		    if (!errors.isEmpty()) {
		      log.error("Ventes n'est pas valide");
		      throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
		    }
		    
		    List<String> articleErrors = new ArrayList<>();

		    dto.getLigneVentes().forEach(ligneVenteDto -> {
		      Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
		      if (article.isEmpty()) {
		        articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId() + " n'a ete trouve dans la BDD");
		      }
		    });

		    if (!articleErrors.isEmpty()) {
		      log.error("One or more articles were not found in the DB, {}", errors);
		      throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
		    }

		    Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));

		      dto.getLigneVentes().forEach(ligneVenteDto -> {
		      LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
		      ligneVente.setVente(savedVentes);
		      ligneVenteRepository.save(ligneVente);
		  
		    });

		    return VentesDto.fromEntity(savedVentes);
		  }
	

	@Override
	public VentesDto findById(Integer id) {
		 if (id == null) {
		      log.error("Ventes ID is NULL");
		      return null;
		    }
		    return ventesRepository.findById(id)
		        .map(VentesDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException("Aucun vente n'a ete trouve dans la BDD", ErrorCodes.VENTE_NOT_FOUND));
		  }

	

	@Override
	public VentesDto findByCode(String code) {
		  if (!StringUtils.hasLength(code)) {
		      log.error("Vente CODE is NULL");
		      return null;
		    }
		    return ventesRepository.findVentesByCode(code)
		        .map(VentesDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune vente client n'a ete trouve avec le CODE " + code, ErrorCodes.VENTE_NOT_VALID
		        ));
	}

	@Override
	public List<VentesDto> findAll() {
		 return ventesRepository.findAll().stream()
			        .map(VentesDto::fromEntity)
			        .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		  if (id == null) {
		      log.error("Vente ID is NULL");
		      return;
		    }
		  
		    ventesRepository.deleteById(id);
		
	}

}
