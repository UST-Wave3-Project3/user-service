package com.example.user_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleDto {
	
	private String userEmail;
	private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userPhoneNo;
    private List<Integer> allRolesId;
    
    
}
