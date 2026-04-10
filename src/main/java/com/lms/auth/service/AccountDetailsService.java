package com.lms.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.auth.model.Account;
import com.lms.auth.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService{
	
	private final AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		return org.springframework.security.core.userdetails.User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole().name())
                .build();
	}

}
