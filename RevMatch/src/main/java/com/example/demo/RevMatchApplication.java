package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RevMatchApplication {
	
	@Autowired
	public static SNSPublisher snspublisher;
	
	public static void main(String[] args) {
		SpringApplication.run(RevMatchApplication.class, args);
		snspublisher.publisher();
	}

}
