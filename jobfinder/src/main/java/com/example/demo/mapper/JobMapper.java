package com.example.demo.mapper;

import com.example.demo.dto.JoobleJobDTO;
import com.example.demo.model.Company;
import com.example.demo.model.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobMapper(){}

    public Job toEntity(JoobleJobDTO joobleJobDTO){
        if (joobleJobDTO == null) {
            throw new IllegalArgumentException("JoobleJobDTO can not be null");
        }
        Company.CompanyBuilder companyBuilder =new Company.CompanyBuilder(joobleJobDTO.getCompany());
        Job.JobBuilder builder = new Job.JobBuilder(joobleJobDTO.getTitle())
                .snippet(joobleJobDTO.getSnippet())
                .salary(joobleJobDTO.getSalary())
                .link(joobleJobDTO.getLink())
                .updated(joobleJobDTO.getUpdated())
                .company(companyBuilder.build());

        return builder.build();
    }
}
