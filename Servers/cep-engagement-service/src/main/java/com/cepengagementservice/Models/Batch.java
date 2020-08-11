package com.cepengagementservice.Models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Cacheable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cepengagementservice.Models.dto.BatchDTO;

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
