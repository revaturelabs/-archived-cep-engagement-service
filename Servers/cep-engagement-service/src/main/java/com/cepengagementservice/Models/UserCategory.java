package com.cepengagementservice.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the OneToMany relationship between UserProfiles and Categories
 * without storing Categories in their own table.
 * 
 * @author sgruv
 *
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "user_category")
public class UserCategory {

	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private int rowId;

	@Column(name = "user_id")
	int userId;

	@Column(name = "category_id")
	int categoryId;

	public UserCategory(int userId, int categoryId) {
		this.userId = userId;
		this.categoryId = categoryId;
	}
}
