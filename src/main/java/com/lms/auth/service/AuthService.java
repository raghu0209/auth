package com.lms.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.auth.dto.AccountLoginDto;
import com.lms.auth.dto.AccountRegisterDto;
import com.lms.auth.model.Account;
import com.lms.auth.model.Role;
import com.lms.auth.repository.AccountRepository;
import com.lms.auth.utils.ApiResponse;
import com.lms.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final AccountRepository accountRepository;
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authenticationManager;

	public ApiResponse register(AccountRegisterDto accountRegisterDto) {
		try {
			if (accountRepository.findByUsername(accountRegisterDto.getUsername()) != null) {
				return ApiResponse.error("Username already exists");
			}
			if (accountRepository.findByEmail(accountRegisterDto.getEmail()) != null) {
				return ApiResponse.error("Email already exists");
			}
			if (accountRepository.findByPhone(accountRegisterDto.getPhone()) != null) {
				return ApiResponse.error("Phone number already exists");
			}
			if (accountRegisterDto.getRole() == null) {
				return ApiResponse.error("Role is required");
			}

			Account account = new Account();
			account.setFirstName(accountRegisterDto.getFirstName());
			account.setLastName(accountRegisterDto.getLastName());
			account.setUsername(accountRegisterDto.getUsername());
			account.setEmail(accountRegisterDto.getEmail());
			account.setPassword(encodePassword(accountRegisterDto.getPassword()));
			account.setPhone(accountRegisterDto.getPhone());
			account.setRole(Role.valueOf(accountRegisterDto.getRole().toUpperCase()));
			if (Role.TUTOR.name().equalsIgnoreCase(accountRegisterDto.getRole())) {
				account.setExperience(accountRegisterDto.getExperience());
				account.setExpertise(accountRegisterDto.getExpertise());
			}

			accountRepository.save(account);
			return ApiResponse.success("Account registered successfully");
		} catch (Exception e) {
			return ApiResponse.error("An error occurred while registering the account: " + e.getMessage());
		}
	}

	public ApiResponse login(AccountLoginDto accountLoginDto) {

		try {

			if (accountLoginDto.getUsername() == null || accountLoginDto.getPassword() == null) {
				return ApiResponse.error("Username and password are required");
			}

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					accountLoginDto.getUsername(), accountLoginDto.getPassword()));

			Account account = accountRepository.findByUsername(accountLoginDto.getUsername());

			String jwtToken = jwtUtil.generateToken(account.getId(), account.getUsername(), account.getRole().name());

			return ApiResponse.success("Login successful", jwtToken);

		} catch (Exception e) {
			return ApiResponse.error("Invalid username or password");
		}
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

}
