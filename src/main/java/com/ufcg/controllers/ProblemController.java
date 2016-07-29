package com.ufcg.controllers;

import com.ufcg.models.Problem;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/problem")
public class ProblemController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String getProblems(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "sort", defaultValue = "date") String sort){
        return "Get Problems";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public String getProblem(@PathVariable("id") String problemId){
        return "Get Problem " + problemId;
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public String createProblem(@RequestBody Problem problem){
        return "Create Problem";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String updateProblem(@PathVariable("id") String problemId, @RequestBody Problem problem){
        return "Update Problem " + problemId + "\n" + problem.toString();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public String deleteProblem(@PathVariable("id") String problemId){
        return "Delete Problem " + problemId;
    }
}