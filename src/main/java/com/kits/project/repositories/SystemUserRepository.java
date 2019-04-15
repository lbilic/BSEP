package com.kits.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kits.project.model.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser,Long> {
	SystemUser findByUsername(String username);
}
