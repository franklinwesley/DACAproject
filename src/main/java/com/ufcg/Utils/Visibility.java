package com.ufcg.Utils;

public enum Visibility {

    PUBLIC("Public"),
    PRIVATE("Private");

    private String description;

    Visibility(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
