package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InfoStudJob {
    private String title;
    private String company;
    private String location;
    private LocalDate expirationDate;
    private List<String> tags;
    private String companyReviewLink;
    private String numberOfReviews;

    public InfoStudJob() {
        this.tags = new ArrayList<>();
    }

    public InfoStudJob(String title, String company, String location, LocalDate expirationDate, List<String> tags, String companyReviewLink, String numberOfReviews) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.expirationDate = expirationDate;
        this.tags = tags;
        this.companyReviewLink = companyReviewLink;
        this.numberOfReviews = numberOfReviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCompanyReviewLink() {
        return companyReviewLink;
    }

    public void setCompanyReviewLink(String companyReviewLink) {
        this.companyReviewLink = companyReviewLink;
    }

    public String getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(String numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    @Override
    public String toString() {
        return "InfoStudJob{" +
                "title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", tags=" + tags +
                ", companyReviewLink='" + companyReviewLink + '\'' +
                ", numberOfReviews='" + numberOfReviews + '\'' +
                '}';
    }
}
