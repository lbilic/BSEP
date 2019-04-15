package com.kits.project.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kits.project.model.Permission;
import com.kits.project.model.Role;
import com.kits.project.model.SystemUser;
import com.kits.project.repositories.PermissionRepository;
import com.kits.project.repositories.RoleRepository;
import com.kits.project.repositories.SystemUserRepository;

@Service
public class SystemUserService {

	@Autowired
	SystemUserRepository userRep;
	
	@Autowired
	RoleRepository roleRep;

	@Autowired
	PermissionRepository permissionRep;
	
	public String addUser(SystemUser user) {
		if(user.getUsername() == null) {
			return "Failed";
		}
		
		if(userRep.findByUsername(user.getUsername())!=null) {
			return "Failed";
		}
		
		SystemUser new_user = new SystemUser(); 
		new_user.setUsername(user.getUsername());
		
		userRep.save(new_user);
		
		return "Successful";
	}
	
	public String editUser(SystemUser user) {
		SystemUser foundUser;
		if((foundUser = userRep.findByUsername(user.getUsername())) == null) {
			return "Failed";
		}
		
		foundUser.getRoles().clear();
		
		if(user.getRoles() != null) {
			for (Role role : user.getRoles()) {
				if(roleRep.findByName(role.getName()) != null) {
					foundUser.getRoles().add(roleRep.findByName(role.getName()));
				}
			}
		}

		userRep.save(foundUser);
		
		return "Successful";
	}
	
	public String removeUser(SystemUser user) {
		if(userRep.findByUsername(user.getUsername())==null) {
			return "Failed";
		}
		
		userRep.delete(userRep.findByUsername(user.getUsername()));
		
		return "Successful";
	}
	
	public String addRole(Role role) {
		if(role.getName() == null) {
			return "Failed";
		}
		
		if(roleRep.findByName(role.getName())!=null) {
			return "Failed";
		}
		
		Role new_role = new Role(); 
		new_role.setName(role.getName());
		
		roleRep.save(new_role);
		
		return "Successful";
	}
	
	public String editRole(Role role) {
		Role foundRole;
		if((foundRole = roleRep.findByName(role.getName())) == null) {
			return "Failed";
		}
		
		foundRole.getPermissions().clear();
		
		if(role.getPermissions() != null) {
			for (Permission permission : role.getPermissions()) {
				if(permissionRep.findByName(permission.getName()) != null) {
					foundRole.getPermissions().add(permissionRep.findByName(permission.getName()));
				}
			}
		}
		
		roleRep.save(foundRole);
		return "Successful";
	}
	
	public String removeRole(Role role) {
		if(roleRep.findByName(role.getName())==null) {
			return "Failed";
		}
		
		roleRep.delete(roleRep.findByName(role.getName()));

		return "Successful";
	}
	
	public String addPermission(Permission permission) {
		if(permissionRep.findByName(permission.getName()) != null) {
			return "Failed";
		}
		
		Permission new_permission = new Permission();
		new_permission.setName(permission.getName());
		
		permissionRep.save(new_permission);
		
		return "Successful";
	}
	
	public String removePermission(Permission permission) {
		if(permissionRep.findByName(permission.getName())==null) {
			return "Failed";
		}
		
		permissionRep.delete(permissionRep.findByName(permission.getName()));
		
		return "Successful";
	}
	
	public List<String> getSystemUsers(){
		List<String> users = new ArrayList<String>();
		for (SystemUser user : userRep.findAll()) {
			users.add(user.getUsername());
		}
		return users;
	}

	public List<String> getRoles(){
		List<String> roles = new ArrayList<String>();
		for (Role role : roleRep.findAll()) {
			roles.add(role.getName());
		}
		return roles;
	}

	public List<String> getPermissions(){
		List<String> permissions = new ArrayList<String>();
		for (Permission permission : permissionRep.findAll()) {
			permissions.add(permission.getName());
		}
		return permissions;
	}
	
}
