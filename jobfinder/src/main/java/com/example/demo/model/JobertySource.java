package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "joberty_source")
public class JobertySource  implements JobSource{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public JobertySource(){}

    @Override
    public String getSourceName() {
        return "Joberty";
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
