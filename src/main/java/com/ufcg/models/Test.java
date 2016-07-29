package com.ufcg.models;

import com.ufcg.Utils.Visibility;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by franklin on 29/07/16.
 */
public class Test implements Serializable{

    private String name;
    private String tip;
    private Map<String, String> inputsOutputs;
    private Visibility type;

    public Test() {}

    public Test(String name, String tip, Map<String, String> inputsOutputs, Visibility type) {
        this.name = name;
        this.tip = tip;
        this.inputsOutputs = inputsOutputs;
        this.type = type;
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
                "name='" + name + '\'' +
                ", tip='" + tip + '\'' +
                ", inputsOutputs=" + inputsOutputs +
                '}';
    }
}
