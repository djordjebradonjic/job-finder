package com.example.demo.model;

public class HelloWorldJobs {
    private String title;
    private String company;
    private String location;
    private String expiryDate;
    private String tags;
    private String seniority;
    private String url;

    public  HelloWorldJobs(String title, String company, String location, String expiryDate, String tags, String seniority, String url) {
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
    public String getExpiryDate() { return expiryDate; }
    public String getTags() { return tags; }
    public String getSeniority() { return seniority; }
    public String getUrl() { return url; }
}
