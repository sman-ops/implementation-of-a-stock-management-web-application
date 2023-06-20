package com.spring.restaurant.services.Impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.restaurant.dto.CommandeClientDto;
import com.spring.restaurant.dto.LigneCommandeClientDto;
import com.spring.restaurant.exception.EntityNotFoundException;
import com.spring.restaurant.exception.ErrorCodes;
import com.spring.restaurant.exception.InvalidEntityException;
import com.spring.restaurant.model.Article;
import com.spring.restaurant.model.Client;
import com.spring.restaurant.model.CommandeClient;
import com.spring.restaurant.model.LigneCommandeClient;
import com.spring.restaurant.repository.ArticleRepository;
import com.spring.restaurant.repository.ClientRepository;
import com.spring.restaurant.repository.CommandeClientRepository;
import com.spring.restaurant.repository.LigneCommandeClientRepository;
import com.spring.restaurant.services.CommandeClientService;
import com.spring.restaurant.validator.CommandeClientValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {
	
	
	  private CommandeClientRepository commandeClientRepository;
	  private LigneCommandeClientRepository ligneCommandeClientRepository;
	  private ClientRepository clientRepository;
	  private ArticleRepository articleRepository;
	

	  @Autowired
	  public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
	      ClientRepository clientRepository, ArticleRepository articleRepository,LigneCommandeClientRepository ligneCommandeClientRepository )
        {
	    this.commandeClientRepository = commandeClientRepository;
	    this.ligneCommandeClientRepository = ligneCommandeClientRepository;
	    this.clientRepository = clientRepository;
	    this.articleRepository = articleRepository;
	   
	  }
	
	
	@Override
	public CommandeClientDto save(CommandeClientDto dto) {
			
		   List<String> errors = CommandeClientValidator.validate(dto);
		   
		   if (!errors.isEmpty()) {
			      log.error("Commande client n'est pas valide");
			      throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
			    }
		   Optional<Client> client = clientRepository.findById(dto.getClient().getId());
		   
		    if (client.isEmpty()) {
		      log.warn("Client with ID {} was not found in the DB", dto.getClient().getId());
		      throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getClient().getId() + " n'a ete trouve dans la BDD",
		          ErrorCodes.CLIENT_NOT_FOUND);
		    }
		    List<String> articleErrors = new ArrayList<>();
		    
		    

		    if (dto.getLigneCommandeClients() != null) {
		      dto.getLigneCommandeClients().forEach(ligCmdClt -> {
		        if (ligCmdClt.getArticle() != null) {
		          Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
		          if (article.isEmpty()) {
		            articleErrors.add("L'article avec l'ID " + ligCmdClt.getArticle().getId() + " n'existe pas");
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
		    CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));
		    	
		    if (dto.getLigneCommandeClients() != null) {
		        dto.getLigneCommandeClients().forEach(ligCmdClt -> {
		          LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
		          ligneCommandeClient.setCommandeClient(savedCmdClt);
		          ligneCommandeClient.setIdEntreprise(dto.getIdEntreprise());
		          LigneCommandeClient savedLigneCmd = ligneCommandeClientRepository.save(ligneCommandeClient);

		
		        });
		      }

		      return CommandeClientDto.fromEntity(savedCmdClt);
			   
	}

	@Override
	public CommandeClientDto findById(Integer id) {
		  if (id == null) {
		      log.error("Commande client ID is NULL");
		      return null;
		    }
		  
		    return commandeClientRepository.findById(id)
		            .map(CommandeClientDto::fromEntity)
		            .orElseThrow(() -> new EntityNotFoundException(
		                "Aucune commande client n'a ete trouve avec l'ID " + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
		            ));
	}

	@Override
	public CommandeClientDto findByCode(String code) {
		  if (!StringUtils.hasLength(code)) {
		      log.error("Commande client CODE is NULL");
		      return null;
		    }
		    return commandeClientRepository.findCommandeClientByCode(code)
		        .map(CommandeClientDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucune commande client n'a ete trouve avec le CODE " + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
		        ));
	}

	@Override
	public List<CommandeClientDto> findAll() {
		 return commandeClientRepository.findAll().stream()
			        .map(CommandeClientDto::fromEntity)
			        .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		   if (id == null) {
			      log.error("Commande client ID is NULL");
			      return;
			    }
			  
			    commandeClientRepository.deleteById(id);
		
	}

}
