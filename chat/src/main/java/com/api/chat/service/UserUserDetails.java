package com.api.chat.service;

import com.api.chat.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserUserDetails implements UserDetails{

    private String name;

    private String password;

    private List<GrantedAuthority> authorities;

    public UserUserDetails(User user,PasswordEncoder key) {
        this.name = user.getEmail();
        this.password = user.getPwd();
        this.authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
 //               .toList();
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
