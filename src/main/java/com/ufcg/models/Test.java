package com.ufcg.models;

import com.ufcg.Utils.Visibility;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Test implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String tip;

    @Column(nullable = false)
    private String input;

    @Column(nullable = false)
    private String output;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Visibility type;

    public Test() {}

    public Test(Long id, String name, String tip, String input, String output, Visibility type) {
        this.id = id;
        this.name = name;
        this.tip = tip;
        this.input = input;
        this.output = output;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Visibility getType() {
        return type;
    }

    public void setType(Visibility type) {
        this.type = type;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tip='" + tip + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", type=" + type +
                '}';
    }
}
