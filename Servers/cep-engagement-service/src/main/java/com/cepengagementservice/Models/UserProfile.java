package com.cepengagementservice.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@OneToOne(fetch = FetchType.LAZY, optional = true, mappedBy = "profile")
	@JoinColumn(name = "user_id", nullable = false)
	@MapsId
	private User user;
	
	@Column(name = "DEADLINE")
	private String batchDeadline = null;
	
	@Column(name = "ASSOCIATE_COUNT")
	private Integer associateCount = null;
	
	public UserProfile() {
		// TODO Auto-generated constructor stub
	}
	
	public UserProfile(String batchDeadline, Integer associateCount) {
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
