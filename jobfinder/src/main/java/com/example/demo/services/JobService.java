package com.example.demo.services;

import com.example.demo.model.Job;
import com.example.demo.exceptions.JobNotFoundException2;
import com.example.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public Job getJobById(Long id){
        return jobRepository.findById(id).orElse(null);
    }

    public Job saveJob(Job job){
        return jobRepository.save(job);
    }

    public  void deleteJob(Long id){
        jobRepository.deleteById(id);
    }

    public Job updateJob(Long id, Job job){
        return jobRepository.findById(id)
                .map(existingJob ->{
                    existingJob.setTitle(job.getTitle());
                    existingJob.setCompany(job.getCompany());
                    existingJob.setDescription(job.getDescription());
                    existingJob.setUrl(job.getUrl());
                    existingJob.setLocation(job.getLocation());
                    return jobRepository.save(existingJob);
                }).orElseThrow(()->new JobNotFoundException2(id));
    }

}
