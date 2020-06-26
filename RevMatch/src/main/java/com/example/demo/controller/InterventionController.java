package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Intervention;
import com.example.demo.service.SNSPublisherService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class InterventionController {
	
	@Autowired
	SNSPublisherService snspublisherservice;
	
	@GetMapping("/")
	public String root() {
		return "test";
	}
	
	@PostMapping("/intervention")
	public String post(@RequestBody Intervention intervention) {
		snspublisherservice.publisher(intervention.getSubject(),intervention.getBody());
		return "Your mail is on the way !";
	}
}
