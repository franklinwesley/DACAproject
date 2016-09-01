package com.ufcg.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Solution implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String code;

    @ManyToOne
    private Problem problem;

    @ManyToOne
    private User creator;

    @OneToMany
    private List<Test> inputsOutputs;

    @Column(nullable = false)
    private boolean resolved;

    private Solution() {}

    public Solution(User creator, String code, Problem problem, List<Test> inputsOutputs) {
        this.creator = creator;
        this.code = code;
        this.inputsOutputs = inputsOutputs;
        this.problem = problem;
        testSolution(problem.getTests());
    }

    public Long getId() {
        return id;
    }

    public User getCreator() {
        return creator;
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

    public List<Test> getInputsOutputs() {
        return inputsOutputs;
    }

    public void setInputsOutputs(List<Test> inputsOutputs) {
        this.inputsOutputs = inputsOutputs;
    }

    public boolean isResolved() {
        return resolved;
    }

    private void testSolution(List<Test> problemTests) {
        //testar solução
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", problem=" + problem +
                ", creator=" + creator +
                ", inputsOutputs=" + inputsOutputs +
                '}';
    }
}
