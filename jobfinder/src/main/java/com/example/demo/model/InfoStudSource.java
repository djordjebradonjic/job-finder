package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "infoStud_source")
public class InfoStudSource implements JobSource{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public InfoStudSource(){}

    public InfoStudSource(Long id) {
        this.id = id;
    }

    @Override
    public String getSourceName() {
        return "InfoStud";
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
