package com.example.demo.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.List;


@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    private String title;
    private String snippet;
    private String salary;
    private String link;
    private String updated;
    @Column(name = "details_link")
    private String detailsLink;
    @ElementCollection
    private List<String> tags;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private JobDetails details;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_id")
    private JobSource source;

    public static class JobBuilder {
        private String title;
        private String snippet;
        private String salary;
        private String link;
        private String updated;
        private String detailsLink;
        private List<String> tags;
        private Company company;
        private JobDetails details;
        private JobSource source;

        public JobBuilder(String title) { // Obavezan title
            this.title = title;
        }

        public JobBuilder snippet(String snippet) {
            this.snippet = snippet;
            return this;
        }

        public JobBuilder salary(String salary) {
            this.salary = salary;
            return this;
        }

        public JobBuilder link(String link) {
            this.link = link;
            return this;
        }

        public JobBuilder updated(String updated) {
            this.updated = updated;
            return this;
        }

        public JobBuilder detailsLink(String detailsLink) {
            this.detailsLink = detailsLink;
            return this;
        }

        public JobBuilder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public JobBuilder company(Company company) {
            this.company = company;
            return this;
        }

        public JobBuilder details(JobDetails details) {
            this.details = details;
            return this;
        }

        public JobBuilder source(JobSource source) {
            this.source = source;
            return this;
        }

        public Job build() {
            return new Job(this);
        }
    }

    public Job(){}

    private Job(JobBuilder builder) {
        this.title = builder.title;
        this.snippet = builder.snippet;
        this.salary = builder.salary;
        this.link = builder.link;
        this.updated = builder.updated;
        this.detailsLink = builder.detailsLink;
        this.tags = builder.tags;
        this.company = builder.company;
        this.details = builder.details;
        this.source = builder.source;
    }

    public JobSource getSource() {
        return source;
    }

    public void setSource(JobSource source) {
        this.source = source;
    }

    public JobDetails getDetails() {
        return details;
    }

    public void setDetails(JobDetails details) {
        this.details = details;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDetailsLink() {
        return detailsLink;
    }

    public void setDetailsLink(String detailsLink) {
        this.detailsLink = detailsLink;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
