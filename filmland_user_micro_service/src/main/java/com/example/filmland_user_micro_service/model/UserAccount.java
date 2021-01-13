package com.example.filmland_user_micro_service.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5, max = 60)
    @Column(unique=true)
    private String userName;

    @NotNull
    @Size(min = 5, max = 60)
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
