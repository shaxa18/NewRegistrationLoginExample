package com.coverstar.service.role;

import java.util.List;
import java.util.Optional;

import com.coverstar.model.Role;

public interface RoleService {

	Optional<Role> findById(Long id);

	Role create(Role role);

	List<Role> findAll();
}
