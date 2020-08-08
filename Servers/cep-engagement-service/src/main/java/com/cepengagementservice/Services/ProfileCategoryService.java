package com.cepengagementservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.ProfileCategory;
import com.cepengagementservice.Repositories.ProfileCategoryRepository;

@Service
public class ProfileCategoryService {

	private ProfileCategoryRepository pcRepo;
	
	private ProfileService profileServ;
	
	private CategoryService categoryService;
	
	public ProfileCategoryService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public ProfileCategoryService(ProfileCategoryRepository pcRepo, ProfileService profileServ,
			CategoryService categoryService) {
		super();
		this.pcRepo = pcRepo;
		this.profileServ = profileServ;
		this.categoryService = categoryService;
	}
	
	public List<ProfileCategory> getAllPCs(){
		return pcRepo.findAll();
	}
	
	public List<ProfileCategory> getPCsByProfileId(int profileId) {
		return pcRepo.findByProfileId(profileId);
	}
	
	public List<ProfileCategory> getPCsByCategoryId(int categoryId) {
		return pcRepo.findByCategoryId(categoryId);
	}
	
	public void deletePCsByProfileId(int profileId) {
		pcRepo.deleteByProfileId(profileId);
		return;
	}
}