package com.ufcg.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufcg.Utils.Visibility;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
public class Problem implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User creator;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String tip;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Test> tests;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Visibility type;

    @Temporal(value=TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    private Calendar date;

    @Transient
    private boolean resolved;

    public Problem() {
        this.resolved = false;
    }

    public Problem(User creator, String name, String description, String tip, List<Test> tests, Visibility type) {
        this();
        this.name = name;
        this.creator = creator;
        this.description = description;
        this.tip = tip;
        this.tests = tests;
        this.type = type;
    }

    @PrePersist
    private void setDate() {
        this.date = Calendar.getInstance();
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public User getCreator() {
        return creator;
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

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Visibility getType() {
        return type;
    }

    public void setType(Visibility type) {
        this.type = type;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", creator=" + creator +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tip='" + tip + '\'' +
                ", tests=" + tests +
                ", type=" + type +
                ", date=" + date +
                ", resolved=" + resolved +
                '}';
    }
}
