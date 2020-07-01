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
	
	@Column(name = "clientname")
	private String clientName;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientCompany == null) ? 0 : clientCompany.hashCode());
		result = prime * result + ((clientContactMethod == null) ? 0 : clientContactMethod.hashCode());
		result = prime * result + ((clientName == null) ? 0 : clientName.hashCode());
		result = prime * result + interventionid;
		result = prime * result + ((numberOfEngineers == null) ? 0 : numberOfEngineers.hashCode());
		result = prime * result + ((skillCategory == null) ? 0 : skillCategory.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}


	@Override
	public String toString() {
		return "Intervention [interventionid=" + interventionid + ", subject=" + subject + ", clientName=" + clientName
				+ ", clientCompany=" + clientCompany + ", clientContactMethod=" + clientContactMethod
				+ ", skillCategory=" + skillCategory + ", numberOfEngineers=" + numberOfEngineers + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intervention other = (Intervention) obj;
		if (clientCompany == null) {
			if (other.clientCompany != null)
				return false;
		} else if (!clientCompany.equals(other.clientCompany))
			return false;
		if (clientContactMethod == null) {
			if (other.clientContactMethod != null)
				return false;
		} else if (!clientContactMethod.equals(other.clientContactMethod))
			return false;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		if (interventionid != other.interventionid)
			return false;
		if (numberOfEngineers == null) {
			if (other.numberOfEngineers != null)
				return false;
		} else if (!numberOfEngineers.equals(other.numberOfEngineers))
			return false;
		if (skillCategory == null) {
			if (other.skillCategory != null)
				return false;
		} else if (!skillCategory.equals(other.skillCategory))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}


	@Column(name = "clientcompany")
	private String clientCompany;
	
	@Column(name = "clientcontactmethod")
	private String clientContactMethod;
	
	@Column(name = "skillcategory")
	private String skillCategory;
	
	@Column(name = "numberofengineers")
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
