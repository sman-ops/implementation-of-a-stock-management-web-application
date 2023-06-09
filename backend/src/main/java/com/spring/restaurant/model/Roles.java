package com.spring.restaurant.model;

import java.math.BigDecimal;

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
@Table(name="roles")
public class Roles extends AbstractEntity {
	
	  @Column(name = "rolename")
	  private String roleName;
	  
	  @ManyToOne
	  @JoinColumn(name = "idutilisateur")
	  private Utilisateur utilisateur;

}
