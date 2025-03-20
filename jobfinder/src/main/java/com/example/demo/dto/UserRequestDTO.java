package com.example.demo.dto;

public class UserRequestDTO {
    private String username;
    private String password;
    private String name;
    private String surname;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }



}
