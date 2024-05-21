package com.api.chat.controller;

import com.api.chat.annotations.Author;
import com.api.chat.annotations.Authors;
import com.api.chat.annotations.Creator;
import com.api.chat.model.User;
import com.api.chat.execeptions.NotAuthorizedUser;
import com.api.chat.execeptions.UserAlreadyException;
import com.api.chat.execeptions.UserNotfoundException;
import com.api.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Authors({@Author(name = "Mithun")})
@Creator(name = "Mithun1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws UserAlreadyException {
        return userService.saveUser(user) ;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public User getUserByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('OWNER')")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/")
    @PreAuthorize("hasAnyRole('USER')")
    public User updateUserData(@RequestBody User user, @RequestParam String email
    , Authentication authentication)
    throws UserNotfoundException, NotAuthorizedUser {
        return userService.updateUser(user,email,authentication);
    }

    @GetMapping("/byId")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public User getUerById(@RequestParam Integer id) throws UserNotfoundException {
        return userService.getById(id);
    }

    @GetMapping("/user")
    public User userLogin(Authentication authentication){
        return userService.getUserByEmail(authentication.getName());
    }
}
