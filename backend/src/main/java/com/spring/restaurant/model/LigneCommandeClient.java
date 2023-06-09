package com.spring.restaurant.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
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
	  
	  @Column(name = "quantite")
	  private BigDecimal quantite;

	  @Column(name = "prixunitaire")
	  private BigDecimal prixUnitaire;



}
