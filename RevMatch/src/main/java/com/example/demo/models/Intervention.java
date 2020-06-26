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
	
	@Column(name = "body")
	private String body;
	
	
	public Intervention() {
		
	}

	public Intervention(int interventionid,String subject,String body) {
		this.interventionid = interventionid;
		this.subject = subject;
		this.body = body;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}


	
}
