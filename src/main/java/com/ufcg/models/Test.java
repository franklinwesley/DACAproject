package com.ufcg.models;

import com.ufcg.Utils.Visibility;

import javax.persistence.*;
import javax.transaction.Transactional;
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

    public Test(String name, String tip, String input, String output, Visibility type) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (input != null ? !input.equals(test.input) : test.input != null) return false;
        return output != null ? output.equals(test.output) : test.output == null;

    }

    @Override
    public int hashCode() {
        int result = input != null ? input.hashCode() : 0;
        result = 31 * result + (output != null ? output.hashCode() : 0);
        return result;
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
