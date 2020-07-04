package com.cepengagementservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.Intervention;
import com.cepengagementservice.Repositories.InterventionRepository;

@Service
public class InterventionService {
	
	@Autowired
	private InterventionRepository ir;
	
	public List<Intervention> findAll(){
		return ir.findAll();
	}
	
	public void addIntervention(Intervention intervention){
		ir.save(intervention);
	}

}
