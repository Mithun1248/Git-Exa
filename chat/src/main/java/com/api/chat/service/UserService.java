package com.api.chat.service;

import com.api.chat.exec.PasswordErrorException;
import com.api.chat.model.User;
import com.api.chat.exec.NotAuthorizedUser;
import com.api.chat.exec.UserAlreadyException;
import com.api.chat.exec.UserNotfoundException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    public User getUserByEmail(String email);
    public List<User> getUsersByEmail(String email);
    public User saveUser(User user) throws UserAlreadyException;
    public User updateUser(User user, String email, Authentication authentication) throws UserNotfoundException, NotAuthorizedUser;
    public User getById(Integer id) throws UserNotfoundException;
    public String login(String username, String password) throws PasswordErrorException, UserNotfoundException;
}
