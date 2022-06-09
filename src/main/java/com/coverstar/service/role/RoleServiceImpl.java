package com.coverstar.service.role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coverstar.dao.role.RoleDao;
import com.coverstar.model.Role;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Optional<Role> findById(Long id) {
		return roleDao.findById(id);
	}

	@Override
	public Role create(Role role) {
		return roleDao.create(role);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

}
