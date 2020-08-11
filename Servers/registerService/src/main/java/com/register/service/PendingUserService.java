package com.register.service;

import java.util.List;

import com.register.model.PendingUser;

/**
 * This interface provides crud operations for a pending user
 * @author Unknown
 *
 */
public interface PendingUserService {

	public void addUser(PendingUser user);
	
	public void deleteUser(PendingUser user);
	
	public PendingUser findById(int userId);
	
	public PendingUser findByEmail(String email);
	
	public List<PendingUser> allPendingUsers();
	
	public void updateUser(PendingUser user);
	
}
