package com.noxzfate.template.springapitemplate.persistence.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Account")
public class AccountEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
}
