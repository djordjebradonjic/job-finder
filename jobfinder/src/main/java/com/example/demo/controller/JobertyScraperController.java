package com.example.demo.controller;


import com.example.demo.services.JobertyScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/joberty")
public class JobertyScraperController {

    @Autowired
    JobertyScraperService jobertyScraperService;

    @GetMapping
    public void scrapeJobs(){

        jobertyScraperService.scrape();
    }
}
