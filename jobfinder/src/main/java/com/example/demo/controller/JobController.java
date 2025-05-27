package com.example.demo.controller;


import com.example.demo.dto.JoobleJobDTO;
import com.example.demo.dto.SearchJobRequest;
import com.example.demo.model.Job;
import com.example.demo.services.JobService;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.JobNotFoundException;
import com.example.demo.exceptions.JobNotFoundException2;
import com.example.demo.services.JoobleApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JoobleApiService joobleApiService;

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

    @GetMapping("/search")
    public List<JoobleJobDTO> searchJobs(@RequestBody SearchJobRequest searchJobRequest){
        return joobleApiService.searchJobs(searchJobRequest.getKeywords(),searchJobRequest.getLocation());
    }

    @GetMapping("/fetchBySource/{source}")
    public List<Job> fetchJobs(@PathVariable String source){
        return jobService.findTop20BySource(source);
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
