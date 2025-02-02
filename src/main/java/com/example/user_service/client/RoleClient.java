package com.example.user_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.user_service.dto.UserRoleCreateDto;


@FeignClient(name = "UserRole-service", url = "http://localhost:6566/api/user-roles")
public interface RoleClient {
	
	@GetMapping("/{userId}")
    List<String> getRolesByUserId(@PathVariable int userId);
	
	@PostMapping
    public UserRoleCreateDto createUserRole(@RequestBody UserRoleCreateDto entity);

}
