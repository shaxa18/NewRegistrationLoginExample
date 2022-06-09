package com.coverstar.service.account;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.coverstar.dto.AccountCreateByAdminDto;
import com.coverstar.dto.AccountCreateDto;
import com.coverstar.dto.AccountUpdateByAdminDto;
import com.coverstar.dto.VerifyCodeDto;
import com.coverstar.model.Account;

public interface AccountService {

	public Account create(AccountCreateByAdminDto accountCreateByAdminDto);
	public Account update(AccountUpdateByAdminDto accountUpdateByAdminDto);
	
	public Account createMember(AccountCreateDto accountDto) throws Exception;

	public Account createAdmin(AccountCreateDto accountDto);

	Optional<Account> findByUsernameOrEmail(String username, String email);

	Optional<Account> findByEmail(String email);

	Optional<Account> findByUsername(String username);

	Optional<Account> findById(Long id);

	public void verifyCode(VerifyCodeDto verifyCodeDto);

	public Page<Account> pagination(String search, Pageable pageable);
	
	public void delete(Long id);
}
