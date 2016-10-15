package com.ufcg.models;

import com.ufcg.Utils.UserType;

public class UserDTO {

    private String email;

    private Long id;

    private UserType type;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.id = user.getId();
        this.type = user.getType();
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public UserType getType() {
        return type;
    }
}
