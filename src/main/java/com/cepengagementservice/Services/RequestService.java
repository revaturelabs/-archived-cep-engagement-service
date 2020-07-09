package com.cepengagementservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Repositories.RequestRepository;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	public List<Request> findAll(){
		return requestRepository.findAll();
	}
	
	public void addIntervention(Request intervention){
		requestRepository.save(intervention);
	}

	public Request findByRequestId(int requestId) {
		return requestRepository.findByRequestId(requestId);
	}

    public void updateRequest(Request request) {
		requestRepository.save(request);
	}

	public void deleteByRequestId(int id) {
		requestRepository.deleteById(id);
		
	}

}
