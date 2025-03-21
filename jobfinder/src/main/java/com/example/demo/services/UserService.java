package com.example.demo.services;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RoleRepo roleRepo;

    public Users register(UserRequestDTO user){
        if(userRepo.findByUsername(user.getUsername())  != null)
            return userRepo.findByUsername(user.getUsername());
        Role role = roleRepo.findByName("ROLE_USER")
                .orElseThrow(() -> new EntityNotFoundException("Role with this name doesn't exists."));

        Users new_user= new Users();
        new_user.setPassword(passwordEncoder.encode(user.getPassword()));
        new_user.setName(user.getName());
        new_user.setUsername(user.getUsername());
        new_user.setRole(role);
        new_user.setSurname(user.getSurname());

        return userRepo.save(new_user);

    }


    public String verify(String username, String password) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(username,password));
    if (authentication.isAuthenticated())
            return jwtService.generateToken(username);
    return "Fail";

    }
}
