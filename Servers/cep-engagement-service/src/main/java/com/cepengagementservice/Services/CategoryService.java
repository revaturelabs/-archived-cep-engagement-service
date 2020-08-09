package com.cepengagementservice.Services;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.Category;
import com.cepengagementservice.Models.dto.CategoryDTO;

@Service
public class CategoryService {

	/**
	 * Returns all Categories as a List
	 * @return
	 */
	@Cacheable("AllCategories")
	public List<Category> getAllCategories() {
		return Category.getAllCategories();
	}
	
	/**
	 * Returns all CategoryDTOs as a List
	 * @return
	 */
	@Cacheable("AllCategoryDTOs")
	public List<CategoryDTO> getAllCategoryDTOs() {
		return Category.getAllCategoriesDTO();
	}
	
	/**
	 * Returns a subset of all categories determined by an array of category IDs
	 * @param categoryIds the array of IDs of categories to be returned
	 * @return
	 */
	@Cacheable("SomeCategories")
	public List<Category> getCategoriesByIds(int[] categoryIds) {
		return Category.getCategoriesByIds(categoryIds);
	}
	
	/**
	 * Returns a subset of all categories as DTOs determined by an array of category IDs
	 * @param categoryIds the array of IDs of categories to be returned
	 * @return
	 */
	@Cacheable("SomeCategoryDTOs")
	public List<CategoryDTO> getCategoryDTOsByIds(int[] categoryIds) {
		return Category.getCategoryDTOsByIds(categoryIds);
	}
	
	/**
	 * Returns a single Category, selected by its id
	 * @param categoryId
	 * @return
	 */
	@Cacheable("SingleCategory")
	public Category getCategoryById(int categoryId) {
		return Category.getCategory(categoryId);
	}
	
	/**
	 * Returns a single CategoryDTO, selected by its id
	 * @param categoryId
	 * @return
	 */
	@Cacheable("SingleCategoryDTO")
	public CategoryDTO getCategoryDTOById(int categoryId) {
		return Category.getCategoryDTO(categoryId);
	}
}
