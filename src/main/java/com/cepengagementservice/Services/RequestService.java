package com.cepengagementservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Repositories.RequestRepository;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository RequestRepository;
	
	public List<Request> findAll(){
		return RequestRepository.findAll();
	}
	
	public void addIntervention(Request intervention){
		RequestRepository.save(intervention);
	}

	public Request findByRequestId(int requestId) {
		return RequestRepository.findByRequestId(requestId);
	}

    public void updateRequest(Request request) {
		RequestRepository.save(request);
	}

	public void deleteByRequestId(int id) {
		RequestRepository.deleteById(id);
		
	}

}
