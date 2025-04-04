package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jooble_source")
public class JoobleSource  implements JobSource{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public JoobleSource(){}


    @Override
    public String getSourceName() {
        return "Jooble";
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
