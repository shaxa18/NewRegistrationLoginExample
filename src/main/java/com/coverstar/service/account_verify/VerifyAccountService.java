package com.coverstar.service.account_verify;

import java.util.Optional;

import com.coverstar.model.VerifyAccount;

public interface VerifyAccountService {

	VerifyAccount create(VerifyAccount verifyAccount);
	Optional<VerifyAccount> findByToken(String token);
	Optional<VerifyAccount> findById(Long id);
	
}
