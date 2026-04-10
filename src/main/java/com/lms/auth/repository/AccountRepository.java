package com.lms.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.auth.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByEmail(String email);

	Account findByUsername(String username);

	Account findByPhone(String phone);
}
