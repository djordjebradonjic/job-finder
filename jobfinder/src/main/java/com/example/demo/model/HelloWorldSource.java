package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hello_world_source")
public class HelloWorldSource implements JobSource{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public HelloWorldSource(){};
    public HelloWorldSource(Long id) {
        this.id = id;
    }


    @Override
    public String getSourceName() {
        return "HelloWorld";
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
