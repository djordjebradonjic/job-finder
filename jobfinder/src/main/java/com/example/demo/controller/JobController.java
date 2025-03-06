package com.example.demo.controller;


import com.example.demo.entity.Job;
import com.example.demo.services.JobService;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.JobNotFoundException;
import com.example.demo.exceptions.JobNotFoundException2;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Job job = jobService.getJobById(id);
        if(job == null) throw  new JobNotFoundException(id);
        return jobService.getJobById(id);
    }

    @PostMapping
    public Job  createJob(@RequestBody @Valid Job job){
        if (job == null) throw new  BadRequestException("ObjectIsNull");
        return jobService.saveJob(job);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id){
        if(jobService.getJobById(id) == null) throw new JobNotFoundException2(id);
        jobService.deleteJob(id);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody @Valid Job job){
        if(jobService.getJobById(id)== null) throw new JobNotFoundException2(id);
        return jobService.updateJob(id,job);

    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(BadRequestException ex){

        Map<String, Object> errorResponse= new HashMap<>();
        errorResponse.put("status" , HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Bad Request");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


}
