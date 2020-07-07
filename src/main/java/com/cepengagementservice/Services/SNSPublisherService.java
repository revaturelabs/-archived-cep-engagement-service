package com.cepengagementservice.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NonNull;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Models.Request.RequestType;
import com.cepengagementservice.Models.Request.Status;

import com.cepengagementservice.Services.UserServices;
@Service
public class SNSPublisherService{
	@Autowired
	UserServices userServices;
	//private final String TOPIC_ARN = "arn:aws:sns:us-east-2:667247404250:TalentRequest";

	//private final String EMAIL_SUBJECT = "E-Mail Notification";
	
	//private final String EMAIL_MESSAGE = "You are getting this mail since the endpoint works";
	
	private AmazonSNSClient snsClient = null;

	public void publisher(Request request) {
		try{
		// creating SNS client
		snsClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2).build();

		// Publish message to the topic
		snsClient.publish("arn:aws:sns:us-east-2:667247404250:TalentRequest", " BatchID: "+request.getBatchId()+"\n "+"UserEmail: "+userServices.getUserById(request.getUserId()).getEmail()+"\n "+"StartDate: "+request.getStartTime()+"\n "+
				 "EndDate: "+request.getEndTime()+"\n "+"isAllDay: "+request.getIsAllDay()+"\n "
				+ "Status: "+request.getStatus()+"\n "+"Request type: "+request.getRequestType()
				+"\n "+ "Description: "+request.getDescription(), request.getRequestType().toString());
		}
		catch(Exception e) {
			
			
		}
		
	}

}



