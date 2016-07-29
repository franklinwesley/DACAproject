package com.ufcg.models;

import java.io.Serializable;

/**
 * Created by huawei on 29/07/16.
 */
public class Problem implements Serializable{

    private String name;
    private String description;
    private Long id;

    public Problem() {}

    public Problem(String name, String description, Long id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
