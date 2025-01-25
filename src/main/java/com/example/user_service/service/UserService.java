package com.example.user_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.user_service.repository.UserRepository;
import com.example.user_service.repository.entity.UserEntity;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userInfoRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    
    public UserEntity registerAdmin(UserEntity newAdmin) {
        newAdmin.setUserPassword(passwordEncoder.encode(newAdmin.getUserPassword()));
        return userInfoRepository.saveAndFlush(newAdmin);
    }
	
	public List<UserEntity> getAllUsers(){
		return userInfoRepository.findAll();
	}
	
	public Optional<UserEntity> getAUserById(int uId){
		return userInfoRepository.findById(uId);
	}
	
	public Optional<UserEntity> getUserByEmail(String email) {
        return userInfoRepository.findByUserEmail(email);
    }
	
	public UserEntity addUser(UserEntity newUser) {
		newUser.setUserPassword(passwordEncoder.encode(newUser.getUserPassword()));
		return userInfoRepository.saveAndFlush(newUser);
	}
	
	public UserEntity updateUser(UserEntity editUser) {
		editUser.setUserPassword(passwordEncoder.encode(editUser.getUserPassword()));
		return userInfoRepository.save(editUser);
	}
	
	public void deleteUser(int uId) {
		userInfoRepository.deleteById(uId);
	}
	
	public String generateToken(String email) {
		return jwtService.generateToken(email);
	}
	
	public boolean verifyToken(String token) {
        jwtService.validateToken(token);
        return true;
    }

	
}
