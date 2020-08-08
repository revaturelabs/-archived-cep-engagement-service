package com.cepengagementservice.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "USER_PROFILES")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "profileId")
public class UserProfile {

	@Id
    @Column(name = "PROFILE_ID", updatable=false)
    private Integer profileId;
	
	@OneToOne(mappedBy = "profile")
	@MapsId
	private User user;
	
	@Column(name = "DEADLINE")
	private String batchDeadline;
	
	@Column(name = "ASSOCIATE_COUNT")
	private Integer associateCount;
	
	public UserProfile() {
		// TODO Auto-generated constructor stub
	}
	
	public UserProfile(User user) {
		this.user = user;
		this.batchDeadline = null;
		this.associateCount = null;
	}
	
	public UserProfile(User user, String batchDeadline, Integer associateCount) {
		this.user = user;
		this.batchDeadline = batchDeadline;
		this.associateCount = associateCount;
	}

	public UserProfile(Integer profileId, User user, String batchDeadline, Integer associateCount) {
		super();
		this.profileId = profileId;
		this.user = user;
		this.batchDeadline = batchDeadline;
		this.associateCount = associateCount;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBatchDeadline() {
		return batchDeadline;
	}

	public void setBatchDeadline(String batchDeadline) {
		this.batchDeadline = batchDeadline;
	}

	public Integer getAssociateCount() {
		return associateCount;
	}

	public void setAssociateCount(Integer associateCount) {
		this.associateCount = associateCount;
	}
	
	
}
