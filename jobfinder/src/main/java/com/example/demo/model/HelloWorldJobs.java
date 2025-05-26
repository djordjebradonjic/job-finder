package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

public class HelloWorldJobs {
    private String title;
    private String company;
    private String location;
    private LocalDate expiryDate;
    private List<String> tags;
    private String seniority;
    private String url;

    public  HelloWorldJobs(String title, String company, String location, LocalDate expiryDate, List<String> tags, String seniority, String url) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.expiryDate = expiryDate;
        this.tags = tags;
        this.seniority = seniority;
        this.url = url;
    }

    public String getTitle() { return title; }
    public String getCompany() { return company; }
    public String getLocation() { return location; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public List<String> getTags() { return tags; }
    public String getSeniority() { return seniority; }
    public String getUrl() { return url; }
}
