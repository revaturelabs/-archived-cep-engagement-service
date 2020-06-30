package com.example.demo.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Batch implements Serializable{
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "batchId")
	private String batchId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "startDate")
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "skill")
	private String skill;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "currentWeek")
	private int currentWeek;
	
	
	
	public Batch() {
		
	}

	public Batch(int id, String batchId, String name, Date startDate, Date endDate, String skill,
			String location, int currentWeek) {
		this.id = id;
		this.batchId = batchId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.skill = skill;
		this.location = location;
		this.currentWeek = currentWeek;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCurrentWeek() {
		return currentWeek;
	}

	public void setCurrentWeek(int currentWeek) {
		this.currentWeek = currentWeek;
	}
	
	
	
	
	
	
	
	
}
