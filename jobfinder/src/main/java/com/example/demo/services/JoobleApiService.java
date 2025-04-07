package com.example.demo.services;


import com.example.demo.dto.JoobleJobDTO;
import com.example.demo.dto.JoobleJobResponse;
import com.example.demo.mapper.JobMapper;
import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JoobleApiService {


    @Value("${jooble.api.key}")
    private String apiKey;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    private final RestTemplate restTemplate= new RestTemplate();

    public void searchJobs(String keyWords, String location){

        String url = "https://rs.jooble.org/api/" + apiKey;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("keywords", keyWords);
        requestBody.put("location", location);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> httpEntity = new HttpEntity<>(requestBody,httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(url,httpEntity, String.class);

        if(response.getBody()!= null){
            try{
                JoobleJobResponse jobResponse = objectMapper.readValue(response.getBody(), JoobleJobResponse.class);

                List<Job> joobleJobDTOList = jobResponse.getJobs().stream()
                        .map(jobMapper::toEntity)
                        .toList();
                joobleJobDTOList.forEach(job -> {
                    System.out.println(job.toString());});

                jobRepository.saveAll(joobleJobDTOList);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public void saveJobsToDatabase(List<Job> jobs){
        if(jobs != null  && !jobs.isEmpty())
            jobRepository.saveAll(jobs);
    }
}
