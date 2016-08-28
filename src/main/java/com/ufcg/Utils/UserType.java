package com.ufcg.Utils;

/**
 * Created by franklin on 29/07/16.
 */

public enum UserType {
    ADMINISTRATOR("Administrator"),
    NORMAL("Normal");

    private String description;

    UserType(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
