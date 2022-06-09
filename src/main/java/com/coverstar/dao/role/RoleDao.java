package com.coverstar.dao.role;

import java.util.List;

import com.coverstar.dao.IOperations;
import com.coverstar.model.Role;

public interface RoleDao extends IOperations<Role>{

	List<Role> findAll();
	
}
