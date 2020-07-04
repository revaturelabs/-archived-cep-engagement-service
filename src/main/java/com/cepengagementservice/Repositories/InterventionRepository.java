package com.cepengagementservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cepengagementservice.Models.Intervention;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention,Integer> {

}
