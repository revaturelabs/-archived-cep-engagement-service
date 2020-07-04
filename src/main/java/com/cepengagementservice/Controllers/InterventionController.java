package com.cepengagementservice.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cepengagementservice.Models.Intervention;
import com.cepengagementservice.Services.InterventionService;

@RestController
@CrossOrigin
public class InterventionController {
	
	@Autowired
	private InterventionService is;
	
	@GetMapping("/interventions")
	public List<Intervention> getAllInterventions(){
		return is.findAll();
	}
	
	@PostMapping("/interventions")
	public String addIntervention(@RequestBody Intervention intervention) {
		is.addIntervention(intervention);
		return "Intervention added";
		
	}

}
