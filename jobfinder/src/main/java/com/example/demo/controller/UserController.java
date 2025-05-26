package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.model.Users;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserController {
    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public Users register(@RequestBody UserRequestDTO user){

        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return userService.verify(loginRequest.getUsername(),loginRequest.getPassword());
    }

}
