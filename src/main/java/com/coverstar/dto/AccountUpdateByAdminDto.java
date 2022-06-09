package com.coverstar.dto;

import java.util.HashSet;
import java.util.Set;

import com.coverstar.validator.account.ValidEmail;
import com.coverstar.validator.account.ValidPassword;
import com.coverstar.validator.account.ValidRepeatPassword;
import com.coverstar.validator.account.ValidUpdateEmail;
import com.coverstar.validator.account.ValidUpdateUsername;
import com.coverstar.validator.account.ValidUsername;

@ValidRepeatPassword
@ValidUpdateEmail
@ValidUpdateUsername
public class AccountUpdateByAdminDto {

	private Long id;

	@ValidUsername
	private String username;

	@ValidEmail
	private String email;

	@ValidPassword
	private String password;

	private String repeatPassword;
	private boolean active;
	private Set<Long> roleIds = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}

	@Override
	public String toString() {
		return "AccountCreateByAdminDto [id=" + id + ", username=" + username + ", email=" + email + ", password="
				+ password + ", repeatPassword=" + repeatPassword + ", roleIds=" + roleIds + "]";
	}

}
