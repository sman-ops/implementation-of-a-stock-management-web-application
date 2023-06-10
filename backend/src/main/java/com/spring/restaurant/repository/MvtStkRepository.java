package com.spring.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.MvtStk;

@Repository
public interface MvtStkRepository extends JpaRepository<MvtStk, Integer> {

}
