package com.example.user_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.repository.entity.UserEntity;
import com.example.user_service.service.CustomUserDetails;
import com.example.user_service.service.JwtService;
import com.example.user_service.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<UserEntity>> getAllUsers(){
		return new ResponseEntity<List<UserEntity>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{uId}")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Optional<UserEntity>> getAUserById(@PathVariable int uId){
		return new ResponseEntity<Optional<UserEntity>>(userService.getAUserById(uId),HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity newUser){
		return new ResponseEntity<UserEntity>(userService.addUser(newUser),HttpStatus.OK);
	}
	
	@PutMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity editUser){
		return new ResponseEntity<UserEntity>(userService.updateUser(editUser),HttpStatus.OK);
	}
	
	@DeleteMapping("/{uId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Void> deleteUser(@PathVariable int uId){
		userService.deleteUser(uId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity user) {
        return userService.registerAdmin(user);
    }

    @GetMapping("/validate/token")
    public boolean validateToken(@RequestParam String token) {
        return userService.verifyToken(token);
    }

    @PostMapping("/validate/user")
    public Map<String, String> getToken(@RequestBody UserEntity user) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword()));

        Map<String, String> response = new HashMap<>();
        if (authenticate.isAuthenticated()) {
            String token = userService.generateToken(user.getUserEmail());
            CustomUserDetails customUserDetails = (CustomUserDetails) authenticate.getPrincipal();
            String roleName = customUserDetails.getRoleName(); // Get role name

            response.put("token", token);
            response.put("email", user.getUserEmail());
            response.put("role", roleName); // Add role to the response
        }
        return response;
    }


}
