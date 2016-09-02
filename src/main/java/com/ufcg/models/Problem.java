package com.ufcg.models;

import com.ufcg.Utils.Visibility;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToMany
    private List<Test> tests;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Visibility type;

    public Problem() {}

    public Problem(User creator, String name, String description, String tip, List<Test> tests, Visibility type) {
        this.name = name;
        this.creator = creator;
        this.description = description;
        this.tip = tip;
        this.tests = tests;
        this.type = type;
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
                '}';
    }
}
