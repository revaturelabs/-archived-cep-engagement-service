package com.cepengagementservice.Models.dto;

import lombok.Data;

@Data
/**
 * @Author le7204
 */

public class BatchDTO {

    String batchId;
    String name;
    String startDate;
    String endDate;
    String skill;
    String location;
    String type;
    int goodGrade;
    int passingGrade;
    int currentWeek;
}
