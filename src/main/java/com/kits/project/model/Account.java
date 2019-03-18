package com.kits.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kits.project.DTOs.AccountDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleted;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private List<AccountAuthority> accountAuthorities;

    public Account() {
        this.accountAuthorities = new ArrayList<>();
    }

    public Account(AccountDTO accountDTO) {
        this.username = accountDTO.username;
        this.password = accountDTO.password;
        this.firstName = accountDTO.firstName;
        this.lastName = accountDTO.lastName;
        this.deleted = false;
    }

    public Account(int version, String username, String password, String email, boolean deleted) {
        this.accountAuthorities = new ArrayList<>();
        this.version = version;
        this.username = username;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
        this.email = email;
        this.deleted = deleted;
    }

    public Account(String username, String password) {
        this.accountAuthorities = new ArrayList<>();
        this.username = username;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public Account(int version, String username, String password, String firstName, String lastName, String email, boolean deleted) {
        this.accountAuthorities = new ArrayList<>();
        this.version = version;
        this.username = username;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.deleted = deleted;
    }

    public Account(int version, String username, String password, String firstName, String lastName, String email, boolean deleted, List<AccountAuthority> accountAuthorities) {
        this.accountAuthorities = new ArrayList<>();
        this.version = version;
        this.username = username;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.deleted = deleted;
        this.accountAuthorities = accountAuthorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public List<AccountAuthority> getAccountAuthorities() {
        return accountAuthorities;
    }

    public void setAccountAuthorities(List<AccountAuthority> accountAuthorities) {
        this.accountAuthorities = accountAuthorities;
    }
}
