package com.example.demo.controller;


import com.example.demo.model.HelloWorldJobs;
import com.example.demo.model.JobertyJob;
import com.example.demo.services.JobertyScraperService;
import com.example.demo.services.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("web-scraper")
public class JobScraperController {

    @Autowired
    private ScraperService scraperService;

    @Autowired
    private JobertyScraperService jobertyScraperService;

    @GetMapping("/helloworld")
    public List<HelloWorldJobs> scrapeHelloWorldJobs(){
        return scraperService.scrape();
    }

    @GetMapping("/joberty")
    public List<JobertyJob> scrapeJobertyJobs(){

        return   jobertyScraperService.scrape();
    }

}
