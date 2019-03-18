package com.kits.project.DTOs;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AccountCreateDTO implements Serializable {
    @Valid
    @NotNull
    private LoginDTO loginAccount;
    @NotNull
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private String email;

    public AccountCreateDTO() {
    }

    public AccountCreateDTO(LoginDTO loginAccount, String firstName, String lastName, String email) {
        this.loginAccount = loginAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public LoginDTO getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(LoginDTO loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}