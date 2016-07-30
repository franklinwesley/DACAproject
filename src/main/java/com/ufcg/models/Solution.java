package com.ufcg.models;

import java.io.Serializable;
import java.util.Map;

public class Solution implements Serializable {

    private Long id;
    private String code;
    private Long problem;
    private Map<String,String> inputsOutputs;

    private Solution() {}

    public Solution(Long id, String code, Long problem, Map<String,String> inputsOutputs) {
        this.id = id;
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

    public Long getProblem() {
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
