package com.example.demo.services;

import com.example.demo.model.Users;
import com.example.demo.model.UserPrincipal;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByUsername(username);
        if (users == null) {
            System.out.println("User is not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new  UserPrincipal(users);
    }
}
