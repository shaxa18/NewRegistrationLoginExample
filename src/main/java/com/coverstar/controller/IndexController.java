package com.coverstar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coverstar.dto.AccountCreateDto;
import com.coverstar.dto.VerifyCodeDto;
import com.coverstar.model.Account;
import com.coverstar.repository.AccountRepository;
import com.coverstar.service.account.AccountService;

@Controller
@RequestMapping("/")
public class IndexController {

	
	@Autowired
	private AccountService accountService;

	@GetMapping("sign-up")
	public String signUp(AccountCreateDto accountCreateDto, Model model) {
		return "sign-up";
	}

	@PostMapping("sign-up")
	public String signUp(@Valid AccountCreateDto accountCreateDto, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			return "sign-up";
		}

		Account account = accountService.createMember(accountCreateDto);
		accountCreateDto.setId(account.getId());

		return "redirect:/verify-code";
	}

	@GetMapping("verify-code")
	public String verifyCode(Model model, VerifyCodeDto verifyCodeDto) {
		
		return "verify-code";
	}

	@PostMapping("verify-code")
	public String verifyCodeAction(Model model, @Valid VerifyCodeDto verifyCodeDto, BindingResult result) {
		if(result.hasErrors()) {
			return "verify-code";
		}
		
		accountService.verifyCode(verifyCodeDto);
		
		return "redirect:/login";
	}
	
	@GetMapping
	public String home(Model model, Pageable pageable) {
		return "index";
	}
	
	@GetMapping("cek")
	public String cek(Model model, HttpServletRequest request) {
		return "layout";
	}
	
	@GetMapping("login")
	public String login(Model model, HttpServletRequest request) {
		return "login";
	}
	
	
	
	
}
