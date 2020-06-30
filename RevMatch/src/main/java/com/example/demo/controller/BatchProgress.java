package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Batch;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BatchProgress {
	
	
	@GetMapping("/getBatchDetails")
	public List<Batch> getBatchDetails(){
		
		RestTemplate resttemplate=new RestTemplate();
		Batch [] batch=resttemplate.getForObject("http://34.82.182.44:80/mock/training/batch/current", Batch[].class);
		return Arrays.asList(batch);

	}

	
}
