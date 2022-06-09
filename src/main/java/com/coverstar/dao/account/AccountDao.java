package com.coverstar.dao.account;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.coverstar.dao.IOperations;
import com.coverstar.model.Account;

public interface AccountDao extends IOperations<Account> {

	Optional<Account> findByUsernameOrEmail(String username, String email);

	Optional<Account> findByEmail(String email);

	Optional<Account> findByUsername(String username);

	Page<Account> findAll(Pageable pageable);

	Page<Account> findByUsernameContainingOrEmaiContaining(String search, Pageable pageable);

}