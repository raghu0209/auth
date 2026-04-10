package com.lms.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.auth.dto.AccountLoginDto;
import com.lms.auth.dto.AccountRegisterDto;
import com.lms.auth.service.AuthService;
import com.lms.auth.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/register")
	public ApiResponse register(@RequestBody AccountRegisterDto accountRegisterDto) {
		return authService.register(accountRegisterDto);
	}
	
	@PostMapping("/login")
	public ApiResponse login(@RequestBody AccountLoginDto accountLoginDto) {
		return authService.login(accountLoginDto);
	}
}
