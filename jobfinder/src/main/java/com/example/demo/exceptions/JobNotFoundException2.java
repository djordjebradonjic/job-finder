package com.example.demo.exceptions;

public class JobNotFoundException2 extends  RuntimeException {
    private  final Long jobId;
    public JobNotFoundException2(Long jobId){
        this.jobId= jobId;
    }

    public Long getJobId() {
        return jobId;
    }
}
