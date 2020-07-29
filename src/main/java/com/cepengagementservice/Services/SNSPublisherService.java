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


/**
 * Publish messages to AmazonSNS
 * @author Unknown
 *this is for the messenger client service.  It basically creates a SNS client
 *in AWS and then publishes a message including all kinds of data.  This is to
 *publish requests.  However this has a few flaws including a catch block with nothing
 *in it
 */
@Service
public class SNSPublisherService{
	@Autowired
	UserServices userServices;
	//private final String TOPIC_ARN = "arn:aws:sns:us-east-2:667247404250:TalentRequest";

	//private final String EMAIL_SUBJECT = "E-Mail Notification";
	
	//private final String EMAIL_MESSAGE = "You are getting this mail since the endpoint works";
	
	private AmazonSNSClient snsClient = null;


	/**
	 * Publish message containing users info and token to SNS
	 * So this is the method to publish all information about a request to the SNS client
	 * as previously we create a null snsClient value before we initially create a new
	 * SNS client using the SNS client builder in central region and then print all
	 * the information to that client so that it has all the information regarding a new
	 * request
	 * @param request is a request/intervention with all the required values associated with it
	 */
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



