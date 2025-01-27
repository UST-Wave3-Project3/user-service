package com.example.user_service.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.user_service.repository.entity.UserEntity;

public class CustomUserDetails implements UserDetails {
	
	private final UserEntity user;
	private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; 
    }

    @Override
    public String getPassword() {
        return user.getUserPassword(); // Password field
    }

    @Override
    public String getUsername() {
        return user.getUserEmail(); // Using email as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize this based on your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize this based on your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize this based on your requirements
    }

    @Override
    public boolean isEnabled() {
        return true; // Customize this based on your requirements
    }

    public String getFullName() {
        return user.getUserFirstName() + " " + user.getUserLastName(); // Optional helper method
    }
    
    public String getRoleName() {
        if (authorities != null && !authorities.isEmpty()) {
            return authorities.iterator().next().getAuthority(); // Assuming the role is the first authority
        }
        return "UNKNOWN";
    }

}
