package com.lms.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lms.auth.model.Role;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRegisterDto {
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;       
	private String password;    
	private String phone;
	
	@NonNull
	private String role;
	
	private Long experience; // For tutors
	private String expertise; // For tutors

}
