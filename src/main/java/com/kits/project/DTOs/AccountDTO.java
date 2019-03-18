package com.kits.project.DTOs;

import com.kits.project.model.Account;

import java.io.Serializable;

public class AccountDTO implements Serializable {
    public Long id;

    public String username;

    public String password;

    public String firstName;

    public String lastName;

    public String email;

    public boolean confirmed;

    public AccountDTO() {}

    public AccountDTO(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.username = account.getUsername();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
    }
}
