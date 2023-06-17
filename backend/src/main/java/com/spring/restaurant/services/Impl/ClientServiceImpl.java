package com.spring.restaurant.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restaurant.dto.ClientDto;
import com.spring.restaurant.exception.EntityNotFoundException;
import com.spring.restaurant.exception.ErrorCodes;
import com.spring.restaurant.exception.InvalidEntityException;
import com.spring.restaurant.repository.ClientRepository;
import com.spring.restaurant.services.ClientService;
import com.spring.restaurant.validator.ClientValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
	
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ClientDto save(ClientDto dto) {
		 List<String> errors = ClientValidator.validate(dto);
		    if (!errors.isEmpty()) {
		      log.error("Client is not valid {}", dto);
		      throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
		    }

		    return ClientDto.fromEntity(
		        clientRepository.save(
		            ClientDto.toEntity(dto)
		        )
		    );
	}

	@Override
	public ClientDto findById(Integer id) {
		  if (id == null) {
		      log.error("Client ID is null");
		      return null;
		    }
		    return clientRepository.findById(id)
		        .map(ClientDto::fromEntity)
		        .orElseThrow(() -> new EntityNotFoundException(
		            "Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD",
		            ErrorCodes.CLIENT_NOT_FOUND)
		        );
	}

	@Override
	public List<ClientDto> findAll() {
		 return clientRepository.findAll().stream()
			        .map(ClientDto::fromEntity)
			        .collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		  if (id == null) {
		      log.error("Client ID is null");
		      return;
		    }
		  
		  clientRepository.deleteById(id);
		
	}

}
