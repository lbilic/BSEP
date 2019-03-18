package com.kits.project.repositories;

import com.kits.project.model.AccountAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface AccountAuthorityRepository extends JpaRepository<AccountAuthority, Long> {
    @Query(value = "SELECT AUTHORITY_ID FROM ACCOUNT_AUTHORITY A WHERE A.account_id = :accId", nativeQuery = true)
    int AuthorityByAccId(@Param("accId") Long id);

    ArrayList<AccountAuthority> findAll();
}
