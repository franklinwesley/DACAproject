package com.ufcg.models;

import javax.persistence.*;

@Entity
public class OutputSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String input;

    @Column(nullable = false)
    private String output;

    public OutputSolution() {}

    public OutputSolution(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public Long getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "OutputSolution{" +
                "input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
