package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Intervention;
import com.example.demo.service.InterventionService;
import com.example.demo.service.SNSPublisherService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class InterventionController {
	
	@Autowired
	SNSPublisherService snspublisherservice;
	@Autowired
	InterventionService interventionservice;
	
	@GetMapping("/")
	public String root() {
		return "test";
	}
	
	@PostMapping("/intervention")
	public String post(@RequestBody Intervention intervention) {
		interventionservice.postTalentRequest(intervention);
		System.out.println(intervention);
		try {
		snspublisherservice.publisher(intervention.getSubject(),intervention.getClientName(),intervention.getClientCompany(),intervention.getClientContactMethod(),intervention.getSkillCategory(),intervention.getNumberOfEngineers());
		}
		catch(Exception e){
			
			
		}
		return "Your mail is on the way !";
	}
}
