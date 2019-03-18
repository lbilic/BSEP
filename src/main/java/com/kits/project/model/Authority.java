package com.kits.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "authority", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonManagedReference(value = "authority")
    private List<AccountAuthority> accountAuthorities;

    public Authority() { this.accountAuthorities = new ArrayList<>(); }

    public Authority(String name) {
        this.name = name;
        this.accountAuthorities = new ArrayList<>();
    }

    public Authority(int version, String name, List<AccountAuthority> accountAuthorities) {
        this.version = version;
        this.name = name;
        this.accountAuthorities = accountAuthorities;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<AccountAuthority> getAccountAuthorities() { return accountAuthorities; }

    public void setAccountAuthorities(List<AccountAuthority> accountAuthorities) { this.accountAuthorities = accountAuthorities; }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
