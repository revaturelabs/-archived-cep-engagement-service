package com.cepengagementservice.Models.dto;

import lombok.Data;

@Data
/**
 * If I'm right about how these DTO classes are used, this will be used when
 * fetching categories from Caliber's API.
 * 
 * @author sgruv
 *
 */
public class CategoryDTO {

	Integer categoryId;
	Boolean active;
	String skillCategory;
}
