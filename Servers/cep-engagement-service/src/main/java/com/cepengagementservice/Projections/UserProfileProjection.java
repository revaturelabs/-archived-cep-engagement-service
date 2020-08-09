package com.cepengagementservice.Projections;

/**
 * Used in the UserRepository as a projection so that we can find a User (or
 * Users) and only receive back the profileDeadline and profileCount fields.
 * 
 * @author sgruv
 *
 */
public interface UserProfileProjection {

	String getProfileDeadline();

	Integer getProfileCount();
}
