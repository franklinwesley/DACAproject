package com.ufcg.models;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by franklin on 29/07/16.
 */
public class Solution implements Serializable {

    private Long code;
    private Map<String,String> inputOutput;

    private Solution() {}

    public Solution(Long code, String input, Map<String,String> inputOutput) {
        this.code = code;
        this.inputOutput = inputOutput;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
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
                "code=" + code +
                ", inputOutput=" + inputOutput +
                '}';
    }
}
