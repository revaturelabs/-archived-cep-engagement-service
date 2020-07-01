package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Service
public class SNSPublisherService{
	
	//private final String TOPIC_ARN = "arn:aws:sns:us-east-2:667247404250:TalentRequest";

	//private final String EMAIL_SUBJECT = "E-Mail Notification";
	
	//private final String EMAIL_MESSAGE = "You are getting this mail since the endpoint works";
	
	private AmazonSNSClient snsClient = null;

	public void publisher(String subject,String clientName,String clientCompany,String clientContactMethod,String skillCategory,String numberOfEngineers) {
		try{
		// creating SNS client
		snsClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2).build();

		// Publish message to the topic
		snsClient.publish("arn:aws:sns:us-east-2:667247404250:TalentRequest", " Client Name: "+clientName+"\n "+"Client Company: "+clientCompany+"\n "+"Client Contact Method: "+clientContactMethod+"\n "+"Skill Category of Concern: "+skillCategory+"\n "+"Number Of Engineers Needed: "+numberOfEngineers, subject);
		}
		catch(Exception e) {
			
			
			
		}
		
	}

}



