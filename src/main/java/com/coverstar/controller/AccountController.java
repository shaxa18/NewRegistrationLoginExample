package com.coverstar.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coverstar.dto.AccountCreateByAdminDto;
import com.coverstar.dto.AccountUpdateByAdminDto;
import com.coverstar.model.Account;
import com.coverstar.service.account.AccountService;
import com.coverstar.service.role.RoleService;

@Controller
@RequestMapping("account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RoleService roleService;

	@GetMapping
	public String index(Model model,
			@PageableDefault(size = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		Page<Account> pagination = accountService.pagination(search, pageable);
		model.addAttribute("page", pagination);
		model.addAttribute("key", search);
		return "account/index";
	}

	@GetMapping("add")
	public String add(Model model, AccountCreateByAdminDto accountCreateByAdminDto) {
		model.addAttribute("roles", roleService.findAll());
		return "account/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid AccountCreateByAdminDto accountCreateByAdminDto, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("roles", roleService.findAll());
			return "account/add";
		}
		accountService.create(accountCreateByAdminDto);

		return "redirect:/account";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, AccountUpdateByAdminDto accountUpdateByAdminDto) {
		if (!accountService.findById(id).isPresent()) {
			return "redirect:/account";
		}
		Account account = accountService.findById(id).get();
		accountUpdateByAdminDto.setActive(account.isActive());
		accountUpdateByAdminDto.setId(account.getId());
		accountUpdateByAdminDto.setEmail(account.getEmail());
		accountUpdateByAdminDto.setUsername(account.getUsername());
		accountUpdateByAdminDto
				.setRoleIds(account.getRoles().stream().map(obj -> obj.getId()).collect(Collectors.toSet()));
		model.addAttribute("roles", roleService.findAll());
		return "account/edit";
	}

	@PostMapping("{id}/edit")
	public String add(@PathVariable("id") Long id, Model model, @Valid AccountUpdateByAdminDto accountUpdateByAdminDto,
			BindingResult result) {
		if (!accountService.findById(id).isPresent()) {
			return "redirect:/account";
		}
		if (result.hasErrors()) {
			Account account = accountService.findById(id).get();
			accountUpdateByAdminDto.setActive(account.isActive());
			accountUpdateByAdminDto.setId(account.getId());
			accountUpdateByAdminDto.setEmail(account.getEmail());
			accountUpdateByAdminDto.setUsername(account.getUsername());
			accountUpdateByAdminDto
					.setRoleIds(account.getRoles().stream().map(obj -> obj.getId()).collect(Collectors.toSet()));
			model.addAttribute("roles", roleService.findAll());
			return "account/edit";
		}
		accountService.update(accountUpdateByAdminDto);

		return "redirect:/account";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable("id") Long id, Model model) {
		if (!accountService.findById(id).isPresent()) {
			return "redirect:/account";
		}

		Account account = accountService.findById(id).get();
		model.addAttribute("account", account);
		return "account/info";
	}
	
	@GetMapping("{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") Long id, Model model) {
		if (!accountService.findById(id).isPresent()) {
			return false;
		}

		accountService.delete(id);
		return true;
	}
}
