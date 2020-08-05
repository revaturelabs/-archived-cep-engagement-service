package com.register.service;

import java.util.List;

import com.register.model.PendingUser;

public interface PendingUserService {

	public void addUser(PendingUser user);
	
	public void deleteUser(PendingUser user);
	
	public PendingUser findById(int userId);
	
	public List<PendingUser> allPendingUsers();
	
	public void updateUser(PendingUser user);
	
}
