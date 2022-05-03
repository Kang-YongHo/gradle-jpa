package com.example.demo.modules.education.domain;

import com.example.demo.modules.account.domain.Account;
import com.example.demo.modules.common.type.YN;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Education {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String subject;

    @Enumerated(EnumType.STRING)
    private YN isDelete = YN.N;

    @ManyToMany
    private Set<Account> accounts = new HashSet<>();

    private int accountsCount;

    public void addAccount(Account account){
        this.accounts.add(account);
        this.accountsCount++;
    }

    public void removeAccount(Account account){
        this.accounts.remove(account);
        this.accountsCount--;
    }

    public Education(Long id, String name, String subject, YN isDelete, Set<Account> accounts, int accountsCount) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.isDelete = isDelete;
        this.accounts = accounts;
        this.accountsCount = accountsCount;
    }
}
