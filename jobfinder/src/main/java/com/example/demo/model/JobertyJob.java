package com.example.demo.model;

import java.util.List;

public class JobertyJob {

    private String title;
    private String company;
    private String detailsLink;
    private List<String> tags;

    public JobertyJob(String title, String company, String detailsLink, List<String> tags) {
        this.title = title;
        this.company = company;
        this.detailsLink = detailsLink;
        this.tags = tags;
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

    public String getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(String detailsLink) {
        this.detailsLink = detailsLink;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "JobertyJob{" +
                "title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", detailsLink='" + detailsLink + '\'' +
                ", tags=" + tags +
                '}';
    }
}
