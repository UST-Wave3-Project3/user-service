package com.example.user_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleTokenDto {
	
	private String email;
	private List<String> allRoles;
	private String token;

}
