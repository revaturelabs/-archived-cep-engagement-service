package com.cepengagementservice.Models.dto;

import lombok.Data;

/**
 * Represents a raw Assessment object gotten from Caliber's Assessment API. At
 * the time of writing, used exclusively for its assessmentCategory field so we
 * can combine that with getByBatchId to find a batch's categories.
 * 
 * @author sgruv
 *
 */
@Data
public class AssessmentDTO {

	int assessmentId;
	int rawScore;
	String assessmentTitle;
	String assessmentType;
	int weekNumber;
	String batchId;
	int assessmentCategory;
	String assessmentDate;
}
