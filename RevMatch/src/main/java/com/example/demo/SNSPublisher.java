package com.example.demo;
import org.springframework.stereotype.Component;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Component
public class SNSPublisher {
	
	private static final String TOPIC_ARN = "arn:aws:sns:us-east-2:667247404250:TalentRequest";

	private static final String EMAIL_SUBJECT = "This is a Test SNS Notification Mail";
	
	private static final String EMAIL_MESSAGE = "We are able to successfully trigger notification from SNS";
	
	private static AmazonSNSClient snsClient = null;

	public static void publisher() {
		// creating SNS client
		snsClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2).build();

		// Publish message to the topic
		snsClient.publish(TOPIC_ARN, EMAIL_MESSAGE, EMAIL_SUBJECT);

	}

}



