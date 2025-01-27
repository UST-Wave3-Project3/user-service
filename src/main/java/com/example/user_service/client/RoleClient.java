package com.example.user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.user_service.dto.RoleResponseDto;

@FeignClient(name = "role-service", url = "http://localhost:5555/api/roles")
public interface RoleClient {
	
	@GetMapping("/{roleId}")
    RoleResponseDto getRoleById(@PathVariable int roleId);

    @GetMapping("{roleName}")
    RoleResponseDto getRoleByName(@PathVariable String roleName);

}
