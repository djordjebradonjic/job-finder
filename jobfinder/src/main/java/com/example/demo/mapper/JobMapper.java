package com.example.demo.mapper;

import com.example.demo.dto.JoobleJobDTO;
import com.example.demo.model.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobMapper(){}

    public Job toEntity(JoobleJobDTO joobleJobDTO){
        if (joobleJobDTO == null) {
            throw new IllegalArgumentException("JoobleJobDTO can not be null");
        }
        Job.JobBuilder builder = new Job.JobBuilder(joobleJobDTO.getTitle()) // Title je obavezan
                .snippet(joobleJobDTO.getSnippet())
                .salary(joobleJobDTO.getSalary())
                .link(joobleJobDTO.getLink())
                .updated(joobleJobDTO.getUpdated());

        // Mapiranje složenih atributa
        
        return builder.build();
    }
}
