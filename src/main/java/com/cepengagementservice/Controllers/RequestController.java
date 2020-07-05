package com.cepengagementservice.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Services.RequestService;
import com.cepengagementservice.Services.SNSPublisherService;

@RestController
@CrossOrigin
public class RequestController {
	
	@Autowired
	private RequestService is;
	
	@Autowired
	private SNSPublisherService snsPublisherService;
	
	@GetMapping("/interventions")
	public List<Request> getAllInterventions(){
		return is.findAll();
	}
	
	@PostMapping("/interventions")
	public String addIntervention(@RequestBody Request request) {
		is.addIntervention(request);
		snsPublisherService.publisher(request.getBatchId(),request.getUserId(),request.getStartTime(),request.getEndTime(),request.getIsAllDay(),request.getStatus(),request.getRequestType(),request.getDescription());
		return "Intervention added";
		
	}

}
