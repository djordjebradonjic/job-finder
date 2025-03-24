package com.example.demo.mapper;

import com.example.demo.dto.JoobleJobDTO;
import com.example.demo.model.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobMapper(){}

    public Job toEntity(JoobleJobDTO joobleJobDTO){
        Job job = new Job();
        job.setTitle(joobleJobDTO.getTitle());
        job.setLocation(joobleJobDTO.getLocation());
        job.setSnippet(joobleJobDTO.getSnippet());
        job.setSalary(joobleJobDTO.getSalary());
        job.setSource(joobleJobDTO.getSource());
        job.setType(joobleJobDTO.getType());
        job.setLink(joobleJobDTO.getLink());
        job.setCompany(joobleJobDTO.getCompany());
        job.setUpdated(joobleJobDTO.getUpdated());
        return job;
    }
}
