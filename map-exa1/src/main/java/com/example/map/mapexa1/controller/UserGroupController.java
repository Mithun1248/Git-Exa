package com.example.map.mapexa1.controller;

import com.example.map.mapexa1.model.UserGroup;
import com.example.map.mapexa1.model.Users;
import com.example.map.mapexa1.repo.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class UserGroupController {

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping("/add")
    public String saveUser(@RequestBody UserGroup group){
        UserGroup g = groupRepository.save(group);
        if(g!= null){
            return "Group is created!";
        }
        return "Unable to create group!";
    }

    @GetMapping("/getAll")
    public List<UserGroup> getAllUser(){
        return groupRepository.findAll();
    }
}
 