package com.api.chat.service;

import com.api.chat.model.User;
import com.api.chat.repository.UserRepository;
import com.api.chat.exec.NotAuthorizedUser;
import com.api.chat.exec.UserAlreadyException;
import com.api.chat.exec.UserNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByEmail(String email){
        return userRepository.findAllByEmail(email);
    }

    public User saveUser(User user) throws UserAlreadyException {
        if(userRepository.findByEmail(user.getEmail())==null){
            user.setPwd(passwordEncoder.encode(user.getPwd()));
            return userRepository.save(user);
        }
        else{
            throw new UserAlreadyException("User already Exists");
        }
    }

    @Override
    public User updateUser(User newUser,String email,Authentication authentication) throws UserNotfoundException,NotAuthorizedUser
    {
        if(!email.equals(authentication.getName()))
            throw new NotAuthorizedUser("Not authorized user!");
        User user = userRepository.findByEmail(email);
        if(user!=null){
            if(newUser.getEmail()!= null)
                user.setEmail(newUser.getEmail());
            if(newUser.getPwd()!=null)
                user.setPwd(passwordEncoder.encode(newUser.getPwd()));
            if(newUser.getName()!=null)
                user.setName(newUser.getName());
            if(newUser.getRole()!=null)
                user.setRole(newUser.getRole());
            return userRepository.save(user);
        }
        throw new UserNotfoundException(email);
    }
}
