package com.example.demo.services;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public Users register(Users user){
        return userRepo.save(user);

    }


}
