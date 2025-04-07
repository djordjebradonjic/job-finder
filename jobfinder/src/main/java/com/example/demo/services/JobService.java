package com.example.demo.services;

import com.example.demo.config.ModelMapperConfig;
import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

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
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        modelMapper.map(job,existingJob);

        return jobRepository.save(existingJob);
    }

}
