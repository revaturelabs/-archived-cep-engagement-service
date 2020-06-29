package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Intervention implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interventionid")
	private int interventionid;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "clientName")
	private String clientName;
	
	@Column(name = "clientCompany")
	private String clientCompany;
	
	@Column(name = "clientContactMethod")
	private String clientContactMethod;
	
	@Column(name = "skillCategory")
	private String skillCategory;
	
	@Column(name = "numberOfEngineers")
	private String numberOfEngineers;
	
	public Intervention() {
		
	}
	
	
	public Intervention(int interventionid, String subject, String clientName, String clientCompany,
			String clientContactMethod, String skillCategory, String numberOfEngineers) {
		this.interventionid = interventionid;
		this.subject = subject;
		this.clientName = clientName;
		this.clientCompany = clientCompany;
		this.clientContactMethod = clientContactMethod;
		this.skillCategory = skillCategory;
		this.numberOfEngineers = numberOfEngineers;
	}


	public int getInterventionid() {
		return interventionid;
	}
	

	public void setInterventionid(int interventionid) {
		this.interventionid = interventionid;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public String getClientCompany() {
		return clientCompany;
	}


	public void setClientCompany(String clientCompany) {
		this.clientCompany = clientCompany;
	}


	public String getClientContactMethod() {
		return clientContactMethod;
	}


	public void setClientContactMethod(String clientContactMethod) {
		this.clientContactMethod = clientContactMethod;
	}


	public String getSkillCategory() {
		return skillCategory;
	}


	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}


	public String getNumberOfEngineers() {
		return numberOfEngineers;
	}


	public void setNumberOfEngineers(String numberOfEngineers) {
		this.numberOfEngineers = numberOfEngineers;
	}


}
