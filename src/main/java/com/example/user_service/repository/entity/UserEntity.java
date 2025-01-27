package com.example.user_service.repository.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="user_service")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
    private int userId;
    
	@Column(name="user_email")
    private String userEmail;
    
	@Column(name="user_password")
    private String userPassword;
    
	@Column(name="user_firstname")
    private String userFirstName;
    
	@Column(name="user_lastname")
    private String userLastName;
    
	@Column(name="user_phoneno")
    private String userPhoneNo;
	
	private int roleId;

}
