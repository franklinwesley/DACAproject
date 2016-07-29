package com.ufcg.models;

import java.io.Serializable;

public class User implements Serializable{

    private String password;
    private String email;
    private Long id;

    public User(){

    }

    public User(String email, String password, Long id){
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return "Email : " + this.email;
    }
}
