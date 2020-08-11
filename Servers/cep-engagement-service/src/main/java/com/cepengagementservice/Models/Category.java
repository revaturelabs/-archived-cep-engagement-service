package com.cepengagementservice.Models;

import lombok.Data;

/**
 * Represents the Category object when retrieved from Caliber
 * 
 * @author sgruv
 *
 */
@Data
public class Category {

	Integer categoryId;
	String skillCategory;
	Boolean active;

}
