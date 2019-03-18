package com.kits.project.services.interfaces;

import com.kits.project.DTOs.AccountDTO;
import com.kits.project.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface AccountService {

    Account findByUsername(String username);

    boolean isUsernameTaken(String username);

    Account updateUser(Long userID, AccountDTO accountDTO);

    boolean archiveUser(Long userID);

    Account login(AccountDTO accountDTO);

    void checkUsername(String username);

    Account save(Account account);

    ArrayList<Account> getAllUsers();
}
