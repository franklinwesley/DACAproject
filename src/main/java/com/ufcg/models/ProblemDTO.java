package com.ufcg.models;

public class ProblemDTO {

    private Long id;

    private String name;

    private String description;

    private String createdAt;

    private boolean resolved;

    public ProblemDTO(Long id, String name, String description, String date, boolean resolved){
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = date;
        this.resolved = resolved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {

        if (description.length() > 60){
            description = description.substring(0, 60);
        }

        description = description + "...";

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
