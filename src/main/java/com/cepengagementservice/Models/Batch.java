package com.example.demo.Models;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.*;

public class Batch {

    public Batch(){};
    public Batch(String batchId, String name, String startDate, String endDate, String skill, String location,
    String type, int goodGrade, int passingGrade, ArrayList<Object> employeeAssignments, ArrayList<Object> associateAssignments){
        this.batchId = batchId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.skill = skill;
        this.location = location;
        this.type = type;
        this.goodGrade = goodGrade;
        this.passingGrade = passingGrade;
	this.nothing = employeeAssignments;
	this.associateAssignments = associateAssignments;
    };
    
    public String batchId;
    public String name;
    public String startDate;
    public String endDate;
    public String skill;
    public String location;
    public String type;
    public int goodGrade;
    public int passingGrade;
    @JsonAlias({"employeeAssignments"})
    public ArrayList<Object> nothing;
    public ArrayList<Object> associateAssignments;

    

}
