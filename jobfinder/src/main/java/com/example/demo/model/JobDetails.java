package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "job_details")
public class JobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private String expirationDate;
    private String expiryDate;
    private String seniority;
    private String url;

    private JobDetails(JobDetailsBuilder builder) {
        this.location = builder.location;
        this.expirationDate = builder.expirationDate;
        this.expiryDate = builder.expiryDate;
        this.seniority = builder.seniority;
        this.url = builder.url;
    }
    public static class JobDetailsBuilder {
        private String location;
        private String expirationDate;
        private String expiryDate;
        private String seniority;
        private String url;

        public JobDetailsBuilder() {
        }

        public JobDetailsBuilder location(String location) {
            this.location = location;
            return this;
        }

        public JobDetailsBuilder expirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public JobDetailsBuilder expiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
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
