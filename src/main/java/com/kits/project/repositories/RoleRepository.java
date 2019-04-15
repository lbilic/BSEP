package com.kits.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kits.project.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	Role findByName(String name);

}
