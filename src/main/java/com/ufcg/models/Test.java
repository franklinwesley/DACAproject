package com.ufcg.models;

import com.ufcg.Utils.Visibility;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
public class Test implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String tip;

    @ElementCollection(fetch=FetchType.EAGER)
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
    private Map<String, String> inputsOutputs;

    @Enumerated(EnumType.ORDINAL)
    private Visibility type;

    public Test() {}

    public Test(Long id, String name, String tip, Map<String, String> inputsOutputs, Visibility type) {
        this.id = id;
        this.name = name;
        this.tip = tip;
        this.inputsOutputs = inputsOutputs;
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

    public Map<String, String> getInputsOutputs() {
        return inputsOutputs;
    }

    public void setInputsOutputs(Map<String, String> inputsOutputs) {
        this.inputsOutputs = inputsOutputs;
    }

    public Visibility getType() {
        return type;
    }

    public void setType(Visibility type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tip='" + tip + '\'' +
                ", inputsOutputs=" + inputsOutputs +
                ", type=" + type +
                '}';
    }
}
