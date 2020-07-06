package com.cepengagementservice.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cepengagementservice.Models.dto.BatchDTO;

import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Data
/**
 * @Author le7204
 */
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

    // TODO: abstract URIs
    public static Batch getBatchById(String id) {
        final String uri = "http://34.82.182.44:80/mock/training/batch/{id}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        RestTemplate restTemplate = new RestTemplate();
        Batch result = restTemplate.getForObject(uri, Batch.class, params);

        return result;
    }

    public static BatchDTO getBatchDTOById(String id) {
        final String uri = "http://34.82.182.44:80/mock/training/batch/{id}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        RestTemplate restTemplate = new RestTemplate();
        BatchDTO result = restTemplate.getForObject(uri, BatchDTO.class, params);

        return result;
    }

}
