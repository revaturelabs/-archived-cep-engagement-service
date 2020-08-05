package com.register.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.model.PendingUser;

public interface PendingUserRepo extends JpaRepository<PendingUser, Integer> {

	public PendingUser findByUserId(int userId);
	
	public PendingUser findByEmail(String email);
	
	public List<PendingUser> findByStatus(String status);
	
}
