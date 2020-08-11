package com.cepengagementservice.Repositories;

import java.util.List;

import javax.persistence.Cacheable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cepengagementservice.Models.UserCategory;

/**
 * Repository for accessing ProfileCategory objects from database via SpringData
 * 
 * @author sgruv
 *
 */
@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Integer> {

	/**
	 * Finds ProfileCategory pairs from the database by profileId
	 * 
	 * @param profileId the id of the profile we are finding categories for
	 * @return List<ProfileCategory> of profile-category pairs for the given profile
	 */
//	@Query("select pc from #{#entityName} pc where pc.profileId = ?1")
//	@Cacheable()
	public List<UserCategory> findByUserId(int userId);

	/**
	 * Finds ProfileCategory pairs from the database
	 * 
	 * @param categoryId the id of the category we are finding categories for
	 * @return List<ProfileCategory> of profile-category pairs for the given
	 *         category
	 */
//	@Query("select pc from #{#entityName} pc where pc.categoryId = ?1")
//	@Cacheable()
	public List<UserCategory> findByCategoryId(int categoryId);

	/**
	 * @return All ProfileCategory objects
	 */
//	@Cacheable()
	public List<UserCategory> findAll();

	/**
	 * This method deletes all of the ProfileCategory pairs with a certain
	 * profileId. In other words, wipes a profile's categories. The profileId should
	 * be the same as its user's id. Used when resetting a Profile's needed
	 * categories.
	 * 
	 * @param userId
	 */
	@Transactional
	@Modifying
	public void deleteByUserId(int userId);
}
