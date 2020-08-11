package com.cepengagementservice.Repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cepengagementservice.Models.Category;
import com.cepengagementservice.Models.dto.CategoryDTO;

@Repository
public class CategoryRepository {
	
	@Value("${caliber.api.category}")
	String caliberCategoryUri;
	
	/**
	 * Retrieves all categories from Caliber's Category API
	 * 
	 * @return
	 */
	public List<Category> getAllCategories() {
		final String uri = caliberCategoryUri; // Hits Caliber's gatAllCategories route
		System.out.println(uri);

		RestTemplate restTemplate = new RestTemplate();
		Category[] resultArr = restTemplate.getForObject(uri, Category[].class);

		List<Category> resultList = new ArrayList<>(Arrays.asList(resultArr));

		return resultList;
	}

	/**
	 * Retrieves all categories from Caliber's Category API as DTOs
	 * 
	 * @return
	 */
	public List<CategoryDTO> getAllCategoriesDTO() {
		final String uri = caliberCategoryUri; // Hits Caliber's gatAllCategories route
		System.out.println(uri);
		
		RestTemplate restTemplate = new RestTemplate();
		CategoryDTO[] resultArr = restTemplate.getForObject(uri, CategoryDTO[].class);

		List<CategoryDTO> resultList = new ArrayList<>(Arrays.asList(resultArr));

		return resultList;
	}

	/**
	 * Retrieves one category by its id from Caliber's Category API
	 * 
	 * @return
	 */
	public Category getCategory(int id) {
		final String uri = caliberCategoryUri + "/" + id;
		System.out.println(uri);

		RestTemplate restTemplate = new RestTemplate();
		Category result = restTemplate.getForObject(uri, Category.class);

		return result;
	}

	/**
	 * Retrieves one category by its id from Caliber's Category API as DTO
	 * 
	 * @return
	 */
	public CategoryDTO getCategoryDTO(int id) {
		final String uri = caliberCategoryUri + "/" + id;
		System.out.println(uri);

		RestTemplate restTemplate = new RestTemplate();
		CategoryDTO result = restTemplate.getForObject(uri, CategoryDTO.class);

		return result;
	}

	/**
	 * Retrieves a list of categories by a given list of ids
	 * 
	 * @return
	 */
	public List<Category> getCategoriesByIds(int[] ids) {
		StringBuilder idList = new StringBuilder();

		for (int tempId : ids) { // creates a comma-separated list of the ids to use as a parameter for the uri
			idList.append(tempId + ",");
		}

		final String uri = caliberCategoryUri + "/ids/" + idList.toString();
		System.out.println(uri);

		RestTemplate restTemplate = new RestTemplate();
		Category[] resultArr = restTemplate.getForObject(uri, Category[].class);
		List<Category> resultList = new ArrayList<>(Arrays.asList(resultArr));

		return resultList;
	}

	/**
	 * Retrieves one category by its id from Caliber's Category API as DTOs
	 * 
	 * @return
	 */
	public List<CategoryDTO> getCategoryDTOsByIds(int[] ids) {
		StringBuilder idList = new StringBuilder();

		for (int tempId : ids) { // creates a comma-separated list of the ids to use as a parameter for the uri
			idList.append(tempId + ",");
		}

		final String uri = caliberCategoryUri + "/ids/" + idList.toString();
		System.out.println(uri);

		RestTemplate restTemplate = new RestTemplate();
		CategoryDTO[] resultArr = restTemplate.getForObject(uri, CategoryDTO[].class);
		List<CategoryDTO> resultList = new ArrayList<>(Arrays.asList(resultArr));

		return resultList;
	}
}
