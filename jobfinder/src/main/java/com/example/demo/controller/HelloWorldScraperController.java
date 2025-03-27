package com.example.demo.controller;

import com.example.demo.model.Job;
import com.example.demo.services.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/helloworld")
public class HelloWorldScraperController {

    @Autowired
    private ScraperService scraperService;

    @GetMapping
    public List<Job> scrapeJobs(){
        return scraperService.scrape();
    }
}
