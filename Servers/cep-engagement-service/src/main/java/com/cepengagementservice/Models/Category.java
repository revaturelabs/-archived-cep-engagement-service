package com.cepengagementservice.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.cepengagementservice.Models.dto.CategoryDTO;

import lombok.Data;

@Data
/**
 * Represents the Category object when retrieved from Caliber
 * 
 * @author sgruv
 *
 */
public class Category {

	Integer categoryId;
	Boolean active;
	String skillCategory;

	public static List<Category> getAllCategories() {
		final String uri = "http://34.82.182.44:80/mock/category/category"; // Hits Caliber's gatAllCategories route

		RestTemplate restTemplate = new RestTemplate();
		Category[] resultArr = restTemplate.getForObject(uri, Category[].class);

		List<Category> resultList = new ArrayList<>(Arrays.asList(resultArr));

		return resultList;
	}

	public static List<CategoryDTO> getAllCategoriesDTO() {
		final String uri = "http://34.82.182.44:80/mock/category/category"; // Hits Caliber's gatAllCategories route

		RestTemplate restTemplate = new RestTemplate();
		CategoryDTO[] resultArr = restTemplate.getForObject(uri, CategoryDTO[].class);

		List<CategoryDTO> resultList = new ArrayList<>(Arrays.asList(resultArr));

		return resultList;
	}

	public static Category getCategory(int id) {
		final String uri = "http://34.82.182.44:80/mock/category/category/" + id;

		RestTemplate restTemplate = new RestTemplate();
		Category result = restTemplate.getForObject(uri, Category.class);

		return result;
	}

	public static CategoryDTO getCategoryDTO(int id) {
		final String uri = "http://34.82.182.44:80/mock/category/category/" + id;

		RestTemplate restTemplate = new RestTemplate();
		CategoryDTO result = restTemplate.getForObject(uri, CategoryDTO.class);

		return result;
	}

	public static List<Category> getCategoriesByIds(int[] ids) {
		StringBuilder idList = new StringBuilder();

		for (int tempId : ids) { // creates a comma-separated list of the ids to use as a parameter for the uri
			idList.append(tempId + ",");
		}

		final String uri = "http://34.82.182.44:80/mock/category/category/id/" + idList;

		RestTemplate restTemplate = new RestTemplate();
		Category[] resultArr = restTemplate.getForObject(uri, Category[].class);
		List<Category> resultList = new ArrayList<>(Arrays.asList(resultArr));

		return resultList;
	}
	
	public static List<CategoryDTO> getCategoryDTOsByIds(int[] ids) {
		StringBuilder idList = new StringBuilder();

		for (int tempId : ids) { // creates a comma-separated list of the ids to use as a parameter for the uri
			idList.append(tempId + ",");
		}

		final String uri = "http://34.82.182.44:80/mock/category/category/id/" + idList;

		RestTemplate restTemplate = new RestTemplate();
		CategoryDTO[] resultArr = restTemplate.getForObject(uri, CategoryDTO[].class);
		List<CategoryDTO> resultList = new ArrayList<>(Arrays.asList(resultArr));

		return resultList;
	}
}