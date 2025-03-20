package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class WelcomeController {

    @GetMapping ("/")
    public String greeting(HttpServletRequest req){
        return "Welcome to JobFinder application" + req.getSession().getId()  ;

    }


}
