package com.api.chat.service;

import com.api.chat.exec.PasswordErrorException;
import com.api.chat.model.User;
import com.api.chat.repository.UserRepository;
import com.api.chat.exec.NotAuthorizedUser;
import com.api.chat.exec.UserAlreadyException;
import com.api.chat.exec.UserNotfoundException;
import com.api.chat.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

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

    @Override
    public User getById(Integer id) throws UserNotfoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserNotfoundException(id.toString());
    }

    @Override
    public String login(String email, String password) throws PasswordErrorException, UserNotfoundException {

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        Authentication result = customAuthenticationProvider.authenticate(authentication);

        if (result.isAuthenticated()) {
            return "Token";
        } else {
            return null;
        }
    }
}
