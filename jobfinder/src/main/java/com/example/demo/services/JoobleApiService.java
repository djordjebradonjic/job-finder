package com.example.demo.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class JoobleApiService {


    @Value("${jooble.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate= new RestTemplate();

    public String searchJobs(String keyWords, String location){

        String url = "https://rs.jooble.org/api/" + apiKey;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("keywords", keyWords);
        requestBody.put("location", location);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> httpEntity = new HttpEntity<>(requestBody,httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,httpEntity,String.class);
        if(responseEntity.getBody() == null)
            return "Nema podataka.";
        else return responseEntity.getBody();
    }
}
