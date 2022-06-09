package com.coverstar.dto;

import com.coverstar.validator.account_verify.ValidVerifyCode;

public class VerifyCodeDto {

	@ValidVerifyCode
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
