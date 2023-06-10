package com.spring.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.CommandeFournisseur;


@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

}
