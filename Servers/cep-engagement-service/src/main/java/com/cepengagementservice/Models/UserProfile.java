package com.cepengagementservice.Models;

import java.util.List;

public class UserProfile {

	private String batchDeadline = null;
	private Integer associateCount = null;
	private List<Category> neededCategories = null;
	
	public UserProfile() {
		// TODO Auto-generated constructor stub
	}

	public UserProfile(String batchDeadline, Integer associateCount, List<Category> neededCategories) {
		super();
		this.batchDeadline = batchDeadline;
		this.associateCount = associateCount;
		this.neededCategories = neededCategories;
	}

	public UserProfile(String batchDeadline, Integer associateCount, List<Category> neededCategories,
			List<Integer> neededCategoryIds) {
		super();
		this.batchDeadline = batchDeadline;
		this.associateCount = associateCount;
		this.neededCategories = neededCategories;
	}

	public String getBatchDeadline() {
		return batchDeadline;
	}

	public void setBatchDeadline(String batchDeadline) {
		this.batchDeadline = batchDeadline;
	}

	public Integer getAssociateCount() {
		return associateCount;
	}

	public void setAssociateCount(Integer associateCount) {
		this.associateCount = associateCount;
	}

	public List<Category> getNeededCategories() {
		return neededCategories;
	}

	public void setNeededCategories(List<Category> neededCategories) {
		this.neededCategories = neededCategories;
	}
}
