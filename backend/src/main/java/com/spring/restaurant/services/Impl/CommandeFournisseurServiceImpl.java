package com.spring.restaurant.services.Impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.restaurant.dto.CommandeFournisseurDto;
import com.spring.restaurant.dto.LigneCommandeFournisseurDto;
import com.spring.restaurant.exception.EntityNotFoundException;
import com.spring.restaurant.exception.ErrorCodes;
import com.spring.restaurant.exception.InvalidEntityException;
import com.spring.restaurant.model.Article;
import com.spring.restaurant.model.CommandeFournisseur;
import com.spring.restaurant.model.Fournisseur;
import com.spring.restaurant.model.LigneCommandeFournisseur;
import com.spring.restaurant.repository.ArticleRepository;
import com.spring.restaurant.repository.CommandeFournisseurRepository;
import com.spring.restaurant.repository.FournisseurRepository;
import com.spring.restaurant.repository.LigneCommandeFournisseurRepository;
import com.spring.restaurant.services.CommandeFournisseurService;
import com.spring.restaurant.validator.CommandeFournisseurValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {
	
	private CommandeFournisseurRepository commandeFournisseurRepository;
	  private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
	  private FournisseurRepository fournisseurRepository;
	  private ArticleRepository articleRepository;
	  
	  @Autowired
	  public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
	      FournisseurRepository fournisseurRepository, ArticleRepository articleRepository,
	      LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
	    this.commandeFournisseurRepository = commandeFournisseurRepository;
	    this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
	    this.fournisseurRepository = fournisseurRepository;
	    this.articleRepository = articleRepository;
	   
	  }

	@Override
	public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
		 List<String> errors = CommandeFournisseurValidator.validate(dto);

		    if (!errors.isEmpty()) {
		      log.error("Commande fournisseur n'est pas valide");
		      throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
		    }

		  
		    
		    Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
		    
		    if (fournisseur.isEmpty()) {
		      log.warn("Fournisseur with ID {} was not found in the DB", dto.getFournisseur().getId());
		      throw new EntityNotFoundException("Aucun fournisseur avec l'ID" + dto.getFournisseur().getId() + " n'a ete trouve dans la BDD",
		          ErrorCodes.FOURNISSEUR_NOT_FOUND);
		    }

		    List<String> articleErrors = new ArrayList<>();

		    if (dto.getLigneCommandeFournisseurs() != null) {
		      dto.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {
		        if (ligCmdFrs.getArticle() != null) {
		          Optional<Article> article = articleRepository.findById(ligCmdFrs.getArticle().getId());
		          if (article.isEmpty()) {
		            articleErrors.add("L'article avec l'ID " + ligCmdFrs.getArticle().getId() + " n'existe pas");
		          }
		        } else {
		          articleErrors.add("Impossible d'enregister une commande avec un aticle NULL");
		        }
		      });
		    }

		    if (!articleErrors.isEmpty()) {
		      log.warn("");
		      throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
		    }
		    dto.setDateCommande(Instant.now());
		    CommandeFournisseur savedCmdFrs = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

		    if (dto.getLigneCommandeFournisseurs() != null) {
		      dto.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {
		        LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFrs);
		        ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFrs);
		        ligneCommandeFournisseur.setIdEntreprise(savedCmdFrs.getIdEntreprise());
		        LigneCommandeFournisseur saveLigne = ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

		      
		      });
		    }

		    return CommandeFournisseurDto.fromEntity(savedCmdFrs);
	}

	@Override
	public CommandeFournisseurDto findById(Integer id) {
		 if (id == null) {
		      log.error("Commande fournisseur ID is NULL");
		      return null;
		    }
		    return commandeFournisseurRepository.findById(id)
		        .map(CommandeFournisseurDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune commande fournisseur n'a ete trouve avec l'ID " + id, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
		        ));
	}

	@Override
	public CommandeFournisseurDto findByCode(String code) {
		 if (!StringUtils.hasLength(code)) {
		      log.error("Commande fournisseur CODE is NULL");
		      return null;
		    }
		    return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
		        .map(CommandeFournisseurDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune commande fournisseur n'a ete trouve avec le CODE " + code, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
		        ));
	}

	@Override
	public List<CommandeFournisseurDto> findAll() {
		  return commandeFournisseurRepository.findAll().stream()
			        .map(CommandeFournisseurDto::fromEntity)
			        .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		 if (id == null) {
		      log.error("Commande fournisseur ID is NULL");
		      return;
		    }
		 
		 commandeFournisseurRepository.deleteById(id);
		
	}

}
