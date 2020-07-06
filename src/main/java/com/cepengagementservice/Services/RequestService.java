package com.cepengagementservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Repositories.RequestRepository;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository ir;
	
	public List<Request> findAll(){
		return ir.findAll();
	}
	
	public void addIntervention(Request intervention){
		ir.save(intervention);
	}

}
