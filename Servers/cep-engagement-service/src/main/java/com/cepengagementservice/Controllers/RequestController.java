package com.cepengagementservice.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Services.RequestService;
import com.cepengagementservice.Services.SNSPublisherService;

/**
 * Returns All interventions or Adds an intervention
 * @author Unknown
 *
 */
@RestController
@CrossOrigin
public class RequestController {
	
	@Autowired
	private RequestService is;
	
	@Autowired
	private SNSPublisherService snsPublisherService;
	/**
	 * Returns all interventions
	 * @return List<Request> List of everything
	 */
	@GetMapping("/interventions")
	public List<Request> getAllInterventions(){
		//Add more validation 
		return is.findAll();
	}
	
	/**
	 * Adds a new intervention and publish to Simple Notification Service 
	 * @param request
	 * @return String "Intervention added"
	 */
	@PostMapping("/interventions")
	public String addIntervention(@RequestBody Request request) {
		is.addIntervention(request);
		snsPublisherService.publisher(request);
		return "Intervention added";
		
	}
	
	@GetMapping ("/requestInterventionById")
	public List<Request> getInterventionsById(@RequestParam("userId") int id){
		return is.interventionById(id);
	
	}

}
