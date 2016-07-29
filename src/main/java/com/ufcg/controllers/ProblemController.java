package com.ufcg.controllers;

import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/problem")
public class ProblemController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<List<Problem>> getProblems(@RequestParam(value = "page", defaultValue = "1") int page,
                                                     @RequestParam(value = "sort", defaultValue = "date") String sort){
        List<Problem> problems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            problems.add(new Problem((long) i, "Problem " + i, "description", "tip", new ArrayList<>(), Visibility.PRIVATE));
        }
        return new ResponseEntity<>(problems, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Problem> getProblem(@PathVariable("id") Long problemId){
        Problem problem = new Problem(problemId, "Problem " + problemId, "description", "tip", new ArrayList<>(), Visibility.PRIVATE);
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public String createProblem(@RequestBody Problem problem){
        return "Create Problem";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String updateProblem(@PathVariable("id") Long problemId, @RequestBody Problem problem){
        return "Update Problem " + problemId + "\n" + problem.toString();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Problem> deleteProblem(@PathVariable("id") Long problemId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}