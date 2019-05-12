package com.kits.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kits.project.DTOs.RelationsDTO;
import com.kits.project.model.Permission;
import com.kits.project.model.Role;
import com.kits.project.model.SystemUser;
import com.kits.project.services.implementations.SystemUserService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api/systemUser")
public class SystemUserController {
	
	@Autowired
	SystemUserService userService;

    @RequestMapping(
    		value = "/user",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody SystemUser user) {
    	return new ResponseEntity<String>(userService.addUser(user),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/user/{username}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeUser(@PathVariable String username) {
    	return new ResponseEntity<String>(userService.removeUser(username),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editUser(@RequestBody SystemUser user) {
    	return new ResponseEntity<String>(userService.editUser(user),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/role",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRole(@RequestBody Role role) {
    	return new ResponseEntity<String>(userService.addRole(role),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/role/{name}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeRole(@PathVariable String name) {
    	return new ResponseEntity<String>(userService.removeRole(name),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/role",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editUser(@RequestBody Role role) {
    	return new ResponseEntity<String>(userService.editRole(role),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/permission",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRole(@RequestBody Permission permission) {
    	return new ResponseEntity<String>(userService.addPermission(permission),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/remove_permission",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removePermission(@RequestBody Role role) {
    	return new ResponseEntity<String>(userService.removePermission(role.getName()),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers() {
    	return new ResponseEntity<List<String>>(userService.getSystemUsers(),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/role",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRoles() {
    	return new ResponseEntity<List<String>>(userService.getRoles(),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/permission",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPermissions() {
    	return new ResponseEntity<List<String>>(userService.getPermissions(),HttpStatus.OK);
    }
    
    @RequestMapping(
    		value = "/user/{username}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers(@PathVariable String username) {
    	return new ResponseEntity<RelationsDTO>(userService.getUserRoles(username),HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/role/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRoles(@PathVariable String name) {
    	return new ResponseEntity<RelationsDTO>(userService.getRolePermissions(name),HttpStatus.OK);
    }
    
}