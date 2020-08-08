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
@Table(name = "profile_category")
public class ProfileCategory {

	@Id
	@GeneratedValue
	@Column(name = "row_id")
	private int rowId;

	@Column(name = "profile_id")
	int profileId;

	@Column(name = "category_id")
	int categoryId;

	public ProfileCategory(int profileId, int categoryId) {
		this.profileId = profileId;
		this.categoryId = categoryId;
	}
}
