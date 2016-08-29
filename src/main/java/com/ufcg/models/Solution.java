package com.ufcg.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
public class Solution implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String code;

    @ManyToOne
    //JoinColum nome da coluna
    private Problem problem;

    @ElementCollection(fetch=FetchType.LAZY)
    @MapKeyColumn(name = "KEY")
    @Column(name = "VALUE")
    private Map<String,String> inputsOutputs;

    private Solution() {}

    public Solution(String code, Problem problem, Map<String,String> inputsOutputs) {
        this.code = code;
        this.inputsOutputs = inputsOutputs;
        this.problem = problem;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Problem getProblem() {
        return problem;
    }

    public Map<String, String> getInputsOutputs() {
        return inputsOutputs;
    }

    public void setInputsOutputs(Map<String, String> inputsOutputs) {
        this.inputsOutputs = inputsOutputs;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", problem=" + problem +
                ", inputsOutputs=" + inputsOutputs +
                '}';
    }
}
