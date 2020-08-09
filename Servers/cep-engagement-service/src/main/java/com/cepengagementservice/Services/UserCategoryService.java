package com.cepengagementservice.Services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cepengagementservice.Models.Category;
import com.cepengagementservice.Models.UserCategory;
import com.cepengagementservice.Repositories.UserCategoryRepository;

@Service
public class UserCategoryService {

	private UserCategoryRepository ucRepository;

	private CategoryService categoryService;

	public UserCategoryService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public UserCategoryService(UserCategoryRepository pcRepo, CategoryService categoryService) {
		super();
		this.ucRepository = pcRepo;
		this.categoryService = categoryService;
	}

	/**
	 * Creates a list of User-Category pairs using a user Id and a list of
	 * Categories, then saves them to the database.
	 * 
	 * @param userId           the user saving these User-Category Pairs
	 * @param neededCategories the List of Categories that will be made into
	 *                         User-Category pairs
	 */
//	@Transactional
//	@Modifying
	public void addUCsAsGroup(int userId, List<Category> neededCategories) {
		Collection<UserCategory> ucPairs = new HashSet<UserCategory>();

		for (Category tempCat : neededCategories) {
			ucPairs.add(new UserCategory(userId, tempCat.getCategoryId()));
		}

		ucRepository.saveAll(ucPairs);
	}

	public List<UserCategory> getAllUCs() {
		return ucRepository.findAll();
	}

	public List<UserCategory> getUCsByUserId(int userId) {
		return ucRepository.findByUserId(userId);
	}

	public List<UserCategory> getUCsByCategoryId(int categoryId) {
		return ucRepository.findByCategoryId(categoryId);
	}

	public void deleteUCById(int ucId) {
		ucRepository.deleteById(ucId);
		return;
	}

	public void deleteUCsByUserId(int userId) {
		ucRepository.deleteByUserId(userId);
		return;
	}
}
