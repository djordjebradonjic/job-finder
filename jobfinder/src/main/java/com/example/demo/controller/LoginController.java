package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping ("/")
    public String greeting(HttpServletRequest req){
        return "Welcome to JobFinder application" + req.getSession().getId();

    }}
