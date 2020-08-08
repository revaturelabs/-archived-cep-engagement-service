package com.cepengagementservice.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cepengagementservice.Models.ProfileCategory;

/**
 * Repository for accessing ProfileCategory objects from database via SpringData
 * 
 * @author sgruv
 *
 */
@Repository
public interface ProfileCategoryRepository extends JpaRepository<ProfileCategory, Integer> {

	/**
	 * Finds ProfileCategory pairs from the database by profileId
	 * 
	 * @param profileId the id of the profile we are finding categories for
	 * @return List<ProfileCategory> of profile-category pairs for the given profile
	 */
//	@Query("select pc from #{#entityName} pc where pc.profileId = ?1")
//	@Cacheable()
	public List<ProfileCategory> findByProfileId(int profileId);

	/**
	 * Finds ProfileCategory pairs from the database
	 * 
	 * @param categoryId the id of the category we are finding categories for
	 * @return List<ProfileCategory> of profile-category pairs for the given
	 *         category
	 */
//	@Query("select pc from #{#entityName} pc where pc.categoryId = ?1")
//	@Cacheable()
	public List<ProfileCategory> findByCategoryId(int categoryId);

	/**
	 * @return All ProfileCategory objects
	 */
//	@Cacheable()
	public List<ProfileCategory> findAll();

	/**
	 * This method deletes all of the ProfileCategory pairs with a certain
	 * profileId. In other words, wipes a profile's categories. The profileId should
	 * be the same as its user's id. Used when resetting a Profile's needed
	 * categories.
	 * 
	 * @param userId
	 */
	public void deleteByProfileId(int id);
}
