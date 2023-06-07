package com.spring.restaurant.model;

import java.time.Instant;
import java.util.List;

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
@Table(name="lignecommandeclient")
public class LigneCommandeClient extends AbstractEntity {
	
	  @ManyToOne
	  @JoinColumn(name = "idarticle")
	  private Article article;

	  @ManyToOne
	  @JoinColumn(name = "idcommandeclient")
	  private CommandeClient commandeClient;


}
