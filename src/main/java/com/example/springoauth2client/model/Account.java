package com.example.springoauth2client.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@ToString
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    protected Account() {
    }

    public Account(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
