package com.ufcg.models;

import java.io.Serializable;

/**
 * Created by franklin on 29/07/16.
 */
public class Solution implements Serializable {

    private Long code;
    private String output;

    private Solution() {}

    public Solution(Long code, String output) {
        this.code = code;
        this.output = output;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "code=" + code +
                ", output='" + output + '\'' +
                '}';
    }
}
