package com.register.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.model.PendingUser;

/**
 *  This interface provides methods to query the database for retrieving a pending user
 * @author Unknown
 *
 */
public interface PendingUserRepo extends JpaRepository<PendingUser, Integer> {

	public PendingUser findByUserId(int userId);
	
	public PendingUser findByEmail(String email);
	
	public List<PendingUser> findAll();
	
}
