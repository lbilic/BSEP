package com.kits.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "account_authority")
public class AccountAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonBackReference
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonBackReference(value="authority")
    private Authority authority;

    public AccountAuthority() { }

    public AccountAuthority(Account account, Authority authority)
    {
        this.account = account;
        this.authority = authority;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Account getAccount() { return account; }

    public void setAccount(Account account) { this.account = account; }

    public Authority getAuthority() { return authority; }

    public void setAuthority(Authority authority) { this.authority = authority; }

}
