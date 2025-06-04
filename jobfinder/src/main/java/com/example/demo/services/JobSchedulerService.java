package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobSchedulerService {

        @Autowired
        ScraperService scraperService;

        @Autowired
        InfoStudScraperService infoStudScraperService;

        @Autowired
        JobertyScraperService jobertyScraperService;

        @Autowired
        JoobleApiService joobleApiService;

        public JobSchedulerService() {

        }

//        @Scheduled(fixedRate = 2 * 60 * 60 * 1000)
//        public void fetchJobsFromAllSources() {
//            System.out.println("Fetching jobs... " + LocalDateTime.now());
//           scraperService.scrape();
//           infoStudScraperService.scrape();
//           jobertyScraperService.scrape();
//            String keywords = "java";
//            String location = "Serbia";
//            joobleApiService.searchJobs(keywords, location);
//        }
    }

