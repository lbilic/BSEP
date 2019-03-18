package com.kits.project.services.interfaces;

import com.kits.project.model.AccountAuthority;

import java.util.ArrayList;

public interface AccountAuthorityService {
    AccountAuthority save(AccountAuthority accountAuthority);

    void remove(Long id);

    int AuthorityByAccId(Long id);

    ArrayList<AccountAuthority> getAllAccountAuthorities();
}
