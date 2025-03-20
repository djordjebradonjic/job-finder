package com.example.demo.model;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name= "Role")
public class Role  implements GrantedAuthority {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column
    private String name;

    public Role() {}

    public Role(Long id, String name){
        this.id= id;
        this.name= name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
