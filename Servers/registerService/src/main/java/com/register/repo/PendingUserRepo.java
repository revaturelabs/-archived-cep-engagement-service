package com.register.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.model.PendingUser;

public interface PendingUserRepo extends JpaRepository<PendingUser, Integer> {

	public PendingUser findByUserId(int userId);
}
