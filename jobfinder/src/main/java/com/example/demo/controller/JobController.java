package com.example.demo.controller;


import com.example.demo.entity.Job;
import com.example.demo.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public List<Job> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    @PostMapping
    public Job  createJob(@RequestBody Job job){
        return jobService.saveJob(job);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
    }


}
