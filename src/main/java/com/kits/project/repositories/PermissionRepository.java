package com.kits.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kits.project.model.Permission;
import com.kits.project.model.SystemUser;

public interface PermissionRepository extends JpaRepository<Permission,Long>{
	Permission findByName(String name);
}
