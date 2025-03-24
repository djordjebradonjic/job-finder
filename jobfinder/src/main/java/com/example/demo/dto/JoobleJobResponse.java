package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JoobleJobResponse {

    @JsonProperty
    private int totalCount;

    @JsonProperty
    private List<JoobleJobDTO> jobs;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<JoobleJobDTO> getJobs() {
        return jobs;
    }

    public void setJobs(List<JoobleJobDTO> jobs) {
        this.jobs = jobs;
    }
}
