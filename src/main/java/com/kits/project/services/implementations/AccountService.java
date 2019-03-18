package com.kits.project.services.implementations;

import com.kits.project.DTOs.AccountDTO;
import com.kits.project.exception.BadRequestException;
import com.kits.project.exception.NotFoundException;
import com.kits.project.model.Account;
import com.kits.project.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class AccountService implements com.kits.project.services.interfaces.AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public Account findByUsername(String username) {
        Account account = this.accountRepository.findByUsername(username);
        if(account == null) throw new NotFoundException("Account not found!");
        return account;
    }

    @Override
    public Account login(AccountDTO accountDTO) {

        Account user = accountRepository.findByUsernameAndPassword(accountDTO.username,accountDTO.password);

        if(user != null){
            if(user.getPassword().equals(accountDTO.password)){
                return user;
            }
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUsernameTaken(String username) {
        Account account = this.accountRepository.findByUsername(username);
        return account != null;
    }

    @Override
    public Account updateUser(Long userID, AccountDTO accountDTO) {
        return null;
    }

    @Override
    public boolean archiveUser(Long userID) {
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public void checkUsername(String username) {
        Account account = this.accountRepository.findByUsername(username);
        if(account != null) throw new BadRequestException("Username is already used!");
    }

    @Override
    @Transactional(readOnly = false)
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public ArrayList<Account> getAllUsers() {
        ArrayList<Account> allUsers = this.accountRepository.findAll();
        return allUsers;
    }
}
