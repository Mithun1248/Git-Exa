package com.api.chat.security;

import com.api.chat.exec.PasswordErrorException;
import com.api.chat.exec.UserNotfoundException;
import com.api.chat.model.User;
import com.api.chat.repository.UserRepository;
import com.api.chat.service.UserService;
import com.api.chat.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Authentication authenticate(Authentication authentication) throws PasswordErrorException, UserNotfoundException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findByEmail(email);
        if(user!=null){
            if(passwordEncoder.matches(password,user.getPwd())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
                return new UsernamePasswordAuthenticationToken(email,password,authorities);
            }
            else{
                throw new PasswordErrorException("Password didn't match!");
            }
        }
        else{
            throw new UserNotfoundException("User is not found!");
        }
    }
}
