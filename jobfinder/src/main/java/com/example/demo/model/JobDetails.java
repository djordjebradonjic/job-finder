package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "job_details")
public class JobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    private LocalDate expirationDate;

    private String seniority;
    @Column(length = 500)
    private String url;
    public JobDetails() {
    }
    private JobDetails(JobDetailsBuilder builder) {
        this.location = builder.location;
        this.expirationDate = builder.expirationDate;
        this.seniority = builder.seniority;
        this.url = builder.url;
    }
    public static class JobDetailsBuilder {
        private String location;
        private LocalDate expirationDate;
        private String seniority;
        private String url;

        public JobDetailsBuilder() {
        }

        public JobDetailsBuilder location(String location) {
            this.location = location;
            return this;
        }

        public JobDetailsBuilder expirationDate(LocalDate expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }


        public JobDetailsBuilder seniority(String seniority) {
            this.seniority = seniority;
            return this;
        }

        public JobDetailsBuilder url(String url) {
            this.url = url;
            return this;
        }

        public JobDetails build() {
            return new JobDetails(this);
        }
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




    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
