package com.cepengagementservice.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NonNull;

/**
 * This class represents the request table in the h2 database
 * @author Unknown
 *
 */
@Entity
@Table(name= "REQUEST_TABLE")
public class Request {

	//enums for status & request type 
	public enum Status {
	    Pending,Done
	}
	
	public enum RequestType{
		Intervention, Talent, Help
	}
	
	//fields
	@Id
	@NonNull
	@GeneratedValue
	@Column(name = "REQUEST_ID")
	private Integer requestId;
	
	//batchId needs to be a foreign key to User_Batches' batchId
	@NonNull
	@Column(name= "BATCH_ID")
	private String batchId;
	
	//userId mapped to User's table userId
//	@ManyToOne
//	@NonNull
//	@JoinColumn(name="USERS USER_ID")
//	//@Column(name="USER_ID")
//	public Integer userId;
//	//private Integer userId;
	
	@NonNull
    @Column(name = "user_id")
    private Integer userId;
	
	@NonNull
	@Column(name="START_TIME")
	private Date startTime;
	
	@NonNull
	@Column(name="END_TIME")
	private Date endTime;
	
	@NonNull
	@Column(name="IS_ALL_DAY")
	private Boolean isAllDay;
	
	@NonNull
	@Column(name="STATUS")
	private Status status;
	
	@NonNull
	@Column(name="REQUEST_TYPE")
	private RequestType requestType;
	
	@NonNull
	@Column(name="DESCRIPTION")
	private String description;
	
	
	//constructor
	public Request() {
		
	}
	
	public Request(String batchId, int userId, Date startTime, Date endTime, Boolean isAllDay,Status status,RequestType requestType,String description) {
		this.batchId=batchId;
		this.userId=userId;
		this.startTime=startTime;
		this.endTime=endTime;
		this.isAllDay=isAllDay;
		this.status=status;
		this.requestType=requestType;
		this.description=description;
	}
	
	//setters
	public void setRequestId(Integer requestId) {
		this.requestId=requestId;
	}
	public void setBatchId(String batchId) {
		this.batchId=batchId;
	}
	public void setUserId(int userId) {
		this.userId=userId;
	}
	public void setStartTime(Date startTime) {
		this.startTime=startTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime=endTime;
	}
	public void setIsAllDay(Boolean isAllDay) {
		this.isAllDay=isAllDay;
	}
	public void setStatus(Status status) {
		this.status=status;
	}
	public void setRequestType(RequestType requestType) {
		this.requestType=requestType;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	
	//getters
	public Integer getRequestId() {
		return this.requestId;
	}
	public String getBatchId() {
		return this.batchId;
	}
	public int getUserId() {
		return this.userId;
	}
	public Date getStartTime() {
		return this.startTime;
	}
	public Date getEndTime() {
		return this.endTime;
	}
	public Boolean getIsAllDay() {
		return this.isAllDay;
	}
	public Status getStatus() {
		return this.status;
	}
	public RequestType getRequestType() {
		return this.requestType;
	}
	public String getDescription() {
		return this.description;
	}
//	
//	@JsonProperty("userId")
//	private void unPack(Integer userId)
//	{
//		I'm the big gay
//		this.userId = new User();
//		this.userId.setUserId(userId);
//	}
}
