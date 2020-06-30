package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Intervention;
import com.example.demo.repository.InterventionRepository;

@Service
public class InterventionService {
	
	@Autowired
	InterventionRepository interventionrepository;

	public void postTalentRequest(Intervention intervention) {
		interventionrepository.save(intervention);
	}

}
