package com.spring.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="utilisateur")
public class Utilisateur extends AbstractEntity {
	
	  @Column(name = "nom")
	  private String nom;
	  
	  
	  @ManyToOne
	  @JoinColumn(name = "identreprise")
	  private Entreprise entreprise;
}
