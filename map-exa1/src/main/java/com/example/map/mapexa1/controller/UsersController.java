package com.example.map.mapexa1.controller;

import com.example.map.mapexa1.model.Users;
import com.example.map.mapexa1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getAll")
    public List<Users> getAllUser(){
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public String addUser(@RequestBody Users user){
        Users u = userRepository.save(user);
        if(u!= null){
            return "User created!";
        }
        return "Error in creating user!";
    }
}
