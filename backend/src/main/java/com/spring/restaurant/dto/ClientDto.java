package com.spring.restaurant.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.restaurant.model.Client;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientDto {
	
	 private Integer id;
	
	 private String nom;

	  private String prenom;

	  private AdresseDto adresse;

	  private String photo;

	  private String mail;

	  private String numTel;
	  
	  @JsonIgnore
	  private List<CommandeClientDto> commandeClients;
	  
	  public static ClientDto fromEntity(Client client) {
		    if (client == null) {
		      return null;
		    }
		    return ClientDto.builder()
		        .id(client.getId())
		        .nom(client.getNom())
		        .prenom(client.getPrenom())
		        .adresse(AdresseDto.fromEntity(client.getAdresse()))
		        .photo(client.getPhoto())
		        .mail(client.getMail())
		        .numTel(client.getNumTel())
		        .build();
		  }

		  public static Client toEntity(ClientDto dto) {
		    if (dto == null) {
		      return null;
		    }
		    Client client = new Client();
		    client.setId(dto.getId());
		    client.setNom(dto.getNom());
		    client.setPrenom(dto.getPrenom());
		    client.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
		    client.setPhoto(dto.getPhoto());
		    client.setMail(dto.getMail());
		    client.setNumTel(dto.getNumTel());
		    return client;
		  }

}
