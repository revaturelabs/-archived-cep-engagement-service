package com.register.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.model.PendingUser;
import com.register.repo.PendingUserRepo;

@Service
public class PendingUserServiceImpl implements PendingUserService {

	private PendingUserRepo pendingUserRepo;
	
	public PendingUserServiceImpl() {
		
	}
	
	@Autowired
	public PendingUserServiceImpl(PendingUserRepo pendingUserRepo) {
		super();
		this.pendingUserRepo = pendingUserRepo;
	}

	@Override
	public void addUser(PendingUser user) {
		pendingUserRepo.save(user);
		
	}

	@Override
	public void deleteUser(PendingUser user) {
		pendingUserRepo.delete(user);
		
	}

	@Override
	public PendingUser findById(int userId) {
		Optional<PendingUser> user = pendingUserRepo.findById(userId);
		if (user.isPresent())
			return user.get();
		return null;
	}

	@Override
	public List<PendingUser> allPendingUsers() {
		
		return pendingUserRepo.findByStatus("Pending");
	}

	@Override
	public void updateUser(PendingUser user) {
		
		pendingUserRepo.save(user);
		
	}
	
	
}
