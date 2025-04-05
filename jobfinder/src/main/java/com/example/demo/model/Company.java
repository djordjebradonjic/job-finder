package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String reviewLink;
    private int numberOfReviews;

    public Company(){}

    private Company(CompanyBuilder builder) {
        this.name = builder.name;
        this.reviewLink = builder.reviewLink;
        this.numberOfReviews = builder.numberOfReviews;

    }
    public static class CompanyBuilder {
        private String name;
        private String reviewLink;
        private int numberOfReviews;


        public CompanyBuilder(String name) {
            this.name = name;
        }

        public CompanyBuilder reviewLink(String reviewLink) {
            this.reviewLink = reviewLink;
            return this;
        }

        public CompanyBuilder numberOfReviews(int numberOfReviews) {
            this.numberOfReviews = numberOfReviews;
            return this;
        }

        public Company build() {
            return new Company(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getReviewLink() {
        return reviewLink;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }
}
