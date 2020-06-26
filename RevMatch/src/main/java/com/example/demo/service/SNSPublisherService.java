package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Service
public class SNSPublisherService {
	
	private final String TOPIC_ARN = "arn:aws:sns:us-east-2:667247404250:TalentRequest";

	private final String EMAIL_SUBJECT = "E-Mail Notification";
	
	private final String EMAIL_MESSAGE = "You are getting this mail since the endpoint works";
	
	private AmazonSNSClient snsClient = null;

	public void publisher(String subject,String body) {
		// creating SNS client
		snsClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2).build();

		// Publish message to the topic
		snsClient.publish(TOPIC_ARN, body, subject);

	}

}



