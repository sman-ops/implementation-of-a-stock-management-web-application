package com.spring.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.LigneCommandeClient;

@Repository
public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {

}
