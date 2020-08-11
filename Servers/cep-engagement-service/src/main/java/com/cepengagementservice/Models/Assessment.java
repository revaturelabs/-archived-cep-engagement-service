package com.cepengagementservice.Models;

import lombok.Data;

@Data
public class Assessment {

	int assessmentId;
	int rawScore;
	String assessmentTitle;
	String assessmentType;
	int weekNumber;
	String batchId;
	int assessmentCategory;
	String assessmentDate;
	
}
