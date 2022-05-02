package com.example.demo.modules.account.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    private String name;
    private String email;
    private String age;
    private String location;

    public Account(Long id, String name, String email, String age, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.location = location;
    }
}
