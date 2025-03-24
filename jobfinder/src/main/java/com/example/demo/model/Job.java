package com.example.demo.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;


@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
 //   @NotBlank(message = "Title is required")
    private String title;

    @Column
//    @NotBlank(message = "Location is required")
    private String location;

    @Column
 //   @NotBlank(message = "Company name is  required")
    private String company;


    @Column
 //   @NotBlank(message = "Url is required")
  //  @URL(message = "Invalid URL ")
    private String url;

    @Column(columnDefinition = "TEXT")
    private String snippet;

    @Column
    private String salary;

    @Column
    private String source;

    @Column
    private String type;

    @Column
    private String link;

    @Column
    private String updated;

    public Job(){}

    public Job(String title, String location, String company, String snippet,
               String salary, String source, String type, String link, String updated) {
        this.title = title;
        this.location = location;
        this.company = company;
        this.snippet = snippet;
        this.salary = salary;
        this.source = source;
        this.type = type;
        this.link = link;
        this.updated = updated;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }


    public String getCompany() {
        return company;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setCompany(String company) {
        this.company = company;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
