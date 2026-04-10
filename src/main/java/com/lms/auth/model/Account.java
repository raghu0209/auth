package com.lms.auth.model;

import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;       
	private String password;    
	private String phone;
	
	private Role role;
	
	private Long experience; // For tutors
	private String expertise; // For tutors
	
	@CreatedDate
	private Date createdDate;
	
	@LastModifiedDate
	private Date lastUpdatedDate;
}
