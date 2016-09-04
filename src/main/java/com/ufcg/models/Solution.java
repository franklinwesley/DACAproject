package com.ufcg.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OutputSolution> inputsOutputs;

    @Column(nullable = false)
    private boolean resolvedProblem;

    @Transient
    private List<Test> testsFail;

    private Solution() {
        this.resolvedProblem = false;
        this.testsFail = new ArrayList<>();
    }

    public Solution(User creator, String code, Problem problem, List<OutputSolution> inputsOutputs) {
        this();
        this.creator = creator;
        this.code = code;
        this.inputsOutputs = inputsOutputs;
        this.problem = problem;
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

    public List<OutputSolution> getInputsOutputs() {
        return inputsOutputs;
    }

    public void setInputsOutputs(List<OutputSolution> inputsOutputs) {
        this.inputsOutputs = inputsOutputs;
    }

    public boolean isResolvedProblem() {
        return resolvedProblem;
    }

    public void setResolvedProblem(boolean resolvedProblem) {
        this.resolvedProblem = resolvedProblem;
    }

    public List<Test> getTestsFail() {
        return testsFail;
    }

    public void setTestsFail(List<Test> testsFail) {
        this.testsFail = testsFail;
    }

    public List<Test> testSolution() {
        List<Test> result = new ArrayList<>();
        for (Test problemTest : this.problem.getTests()) {
            for (OutputSolution inputOutput : this.inputsOutputs) {
                if (problemTest.getInput().equals(inputOutput.getInput()) &&
                        !problemTest.getOutput().equals(inputOutput.getOutput())) {
                    result.add(problemTest);
                }
            }
        }
        return result;
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
