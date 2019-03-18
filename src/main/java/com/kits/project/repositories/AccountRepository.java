package com.kits.project.repositories;

import com.kits.project.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    Account findByUsernameAndPassword(String username, String password);

    ArrayList<Account> findAll();
}
