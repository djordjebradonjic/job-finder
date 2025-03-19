package com.example.demo.controller;

import com.example.demo.model.Users;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @GetMapping ("/")
    public String greeting(HttpServletRequest req){
        return "Welcome to JobFinder application" + req.getSession().getId();

    }


}
