package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JoobleJobDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("location")
    private String location;

    @JsonProperty("snippet")
    private String snippet;

    @JsonProperty("salary")
    private String salary;

    @JsonProperty("source")
    private String source;

    @JsonProperty("type")
    private String type;

    @JsonProperty("link")
    private String link;

    @JsonProperty("company")
    private String company;

    @JsonProperty("updated")
    private LocalDate updated;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSnippet() { return snippet; }
    public void setSnippet(String snippet) { this.snippet = snippet; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public LocalDate getUpdated() { return updated; }
    public void setUpdated(LocalDate updated) { this.updated = updated; }

}
