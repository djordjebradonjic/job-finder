package com.example.demo.mapper;

import com.example.demo.dto.JoobleJobDTO;
import com.example.demo.model.Company;
import com.example.demo.model.Job;
import com.example.demo.model.JobDetails;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobMapper(){}

    public Job toEntity(JoobleJobDTO joobleJob){
        if (joobleJob == null) {
            throw new IllegalArgumentException("JoobleJobDTO can not be null");
        }
        Company company = new Company.CompanyBuilder(joobleJob.getCompany()).build();
        JobDetails details = new JobDetails.JobDetailsBuilder()
                .location(joobleJob.getLocation())
                .url(joobleJob.getLink())
                .expirationDate(joobleJob.getUpdatedAsLocalDate())
                .build();
        return new Job.JobBuilder(joobleJob.getTitle())
                .company(company)
                .details(details)
                .source("Jooble")
                .build();


    }
}
