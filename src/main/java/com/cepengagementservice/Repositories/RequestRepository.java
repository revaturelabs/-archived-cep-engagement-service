package com.cepengagementservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cepengagementservice.Models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {

}
