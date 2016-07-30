package com.ufcg.models;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by franklin on 29/07/16.
 */
public class Solution implements Serializable {

    private Long id;
    private String code;
    private Problem problem;
    private Map<String,String> inputOutput;

    private Solution() {}

    public Solution(Long id, String code, Problem problem, Map<String,String> inputOutput) {
        this.id = id;
        this.code = code;
        this.inputOutput = inputOutput;
        this.problem = problem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Map<String, String> getInputOutput() {
        return inputOutput;
    }

    public void setInputOutput(Map<String, String> inputOutput) {
        this.inputOutput = inputOutput;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", problem=" + problem +
                ", inputOutput=" + inputOutput +
                '}';
    }
}
