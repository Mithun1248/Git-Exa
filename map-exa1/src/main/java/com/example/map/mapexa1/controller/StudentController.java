package com.example.map.mapexa1.controller;

import com.example.map.mapexa1.model.Student;
import com.example.map.mapexa1.model.UserGroup;
import com.example.map.mapexa1.repo.StudentRepository;
import com.example.map.mapexa1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public String saveUser(@RequestBody Student student){
        Student s = studentRepository.save(student);
        if(s!= null){
            return "Student is created!";
        }
        return "Unable to create student!";
    }
}
