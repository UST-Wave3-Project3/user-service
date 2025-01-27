package com.example.user_service.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.user_service.client.RoleClient;
import com.example.user_service.dto.RoleResponseDto;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.repository.entity.UserEntity;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private RoleClient roleClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        
        RoleResponseDto roleResponse = roleClient.getRoleById(user.getRoleId()); // Assuming roleId is stored in UserEntity

        // Create authorities (role-based)
        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(roleResponse.getRoleName()));

        return new CustomUserDetails(user, authorities);
    }
}
