package com.cepengagementservice.Repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cepengagementservice.Models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {

	@Cacheable("RequestById")
	Request findByRequestId(int requestId);

}
