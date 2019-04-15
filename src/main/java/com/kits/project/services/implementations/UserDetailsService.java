package com.kits.project.services.implementations;

import com.kits.project.model.*;
import com.kits.project.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private SystemUserService systemUserService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(String.format("There is no account with username '%s'.", username));
        } else {
            SystemUser systemUser = systemUserService.getUser(account.getUsername());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Role role : systemUser.getRoles()) {
                for (Permission permission : role.getPermissions()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
                }
            }
            return new org.springframework.security.core.userdetails.User(
                    account.getUsername(),
                    account.getPassword(),
                    grantedAuthorities);
        }
    }
}
