package com.cepengagementservice.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserProfile {

	@Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROFILE_ID", updatable=false)
    private Integer profileId;
	
	@OneToOne(mappedBy = "profile")
	private Integer userId;
	
	@Column(name = "DEADLINE")
	private String batchDeadline;
	
	@Column(name = "ASSOCIATE_COUNT")
	private Integer associateCount;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PROFILE_ID")
	private List<Integer> neededSkills = new ArrayList<>();
}
