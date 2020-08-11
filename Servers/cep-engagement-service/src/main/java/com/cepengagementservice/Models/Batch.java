package com.cepengagementservice.Models;

import java.util.List;

import lombok.Data;

/**
 * This class represents the batch object.
 * The data for the batches will be generated from the caliber service.
 * @Author le7204
 */
@Data
public class Batch {

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
    List<?> employeeAssignments;
    List<?> associateAssignments;

}
