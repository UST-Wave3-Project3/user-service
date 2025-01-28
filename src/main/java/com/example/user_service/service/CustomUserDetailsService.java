package com.example.user_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.user_service.client.RoleClient;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.repository.entity.UserEntity;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userCredentialsDao;

    @Autowired
    private RoleClient roleServiceFeignClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Optional<UserEntity> user = userCredentialsDao.findByUserEmail(email); // Assuming username is unique
        System.out.println("user optional entity : " + user);
        if (user.isPresent()) {
        	List<String> allRoles = null;
            Integer userId = user.get().getUserId();

            List<String> roles;
            
            try {
                allRoles = roleServiceFeignClient.getRolesByUserId(userId);
                
            } catch (Exception e) {
                throw new UsernameNotFoundException("Failed to fetch roles for userId: " + userId, e);
            }
            System.out.println("allRoles : " + allRoles);
            return new CustomUserDetails(user.get(), allRoles);
        } else {
            throw new UsernameNotFoundException("User not found for user email: " + email);
        }
    }
	
}
