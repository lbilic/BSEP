package com.kits.project.controllers;

import com.kits.project.DTOs.LoginDTO;
import com.kits.project.DTOs.TokenDTO;
import com.kits.project.exception.BadRequestException;
import com.kits.project.exception.ForbiddenException;
import com.kits.project.model.Account;
import com.kits.project.model.SystemUser;
import com.kits.project.security.JWTUtils;
import com.kits.project.services.implementations.AccountAuthorityService;
import com.kits.project.services.implementations.AccountService;
import com.kits.project.services.implementations.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api")
public class AccountController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AccountService userServiceInterface;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountAuthorityService accountAuthorityService;

    @Autowired
    private SystemUserService systemUserService;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity login(@Valid @RequestBody LoginDTO loginDTO, BindingResult errors) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            authenticationManager.authenticate(token);
            Account account = this.userServiceInterface.findByUsername(loginDTO.getUsername());

            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            SystemUser systemUser = systemUserService.getUser(account.getUsername());
            Long id = account.getId();
            TokenDTO userToken = new TokenDTO(jwtUtils.generateToken(details, id));
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch(ForbiddenException ex) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/check_username",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity checkUsername(
            @RequestParam("username") String username) {

        if (username == null || username.equals(""))
            throw new BadRequestException("Username can't be empty!");

        return new ResponseEntity(this.userServiceInterface.isUsernameTaken(username), HttpStatus.OK);
    }
}
