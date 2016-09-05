package com.ufcg.models;

import com.ufcg.Utils.UserType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable{

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private UserType type;

    public User(){}

    public User(String email, String password, UserType type){
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", type=" + type +
                '}';
    }
}
