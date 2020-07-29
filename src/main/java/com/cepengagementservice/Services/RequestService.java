package com.cepengagementservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Repositories.RequestRepository;
/**
 * 
 * @author unknown
 * this is the service layer for requests
 *
 */
@Service
public class RequestService {
	
	@Autowired
	private RequestRepository ir;
	/**
	 * this is the method to find all requests
	 * @return a list of all requests
	 */
	public List<Request> findAll(){
		return ir.findAll();
	}
	/**
	 * this is the method to add an intervention/request
	 * @param intervention is the data object for an intervention
	 */
	public void addIntervention(Request intervention){
		ir.save(intervention);
	}
	/**
	 * this is the method used to find a request by the ID of the request
	 * @param requestId is the ID of the request
	 * @return a single request/intervention 
	 */
	public Request findByRequestId(int requestId) {
		return ir.findByRequestId(requestId);
	}
	/**
	 * this is the method used to update a request
	 * @param request is a new request/intervention object and is saved to the repository
	 */
    public void updateRequest(Request request) {
		ir.save(request);
	}
    /**
     * this is the method used to delete any specific request using the ID
     * @param id is the ID of the request
     */
	public void deleteByRequestId(int id) {
		ir.deleteById(id);
		
	}

}
