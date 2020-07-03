package com.cepengagementservice.Models;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Data
/**
 * @Author le7204
 */
public class Batch {

    private static Batch getBatchById(String id) {
        final String uri = "http://34.82.182.44:80/mock/training/batch/{id}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");

        RestTemplate restTemplate = new RestTemplate();
        Batch result = restTemplate.getForObject(uri, Batch.class, params);

        System.out.println(result);
        return result;
    }

}