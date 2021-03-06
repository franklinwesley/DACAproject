package com.ufcg.controllers;

import com.ufcg.models.Solution;
import com.ufcg.models.Test;
import com.ufcg.services.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/solution")
public class SolutionController {

    @Autowired
    SolutionService solutionService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<List<Solution>> getSolutions(@RequestParam(value = "problemId") Long problemId){
        List<Solution> solutions = solutionService.findAllSolutionsOfProblem(problemId);

        if(solutions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(solutions, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Solution> getSolution(@PathVariable("id") Long solutionId){
        Solution solution = solutionService.findById(solutionId);
        if(solution == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(solution, HttpStatus.OK);
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Solution> createSolution(@RequestBody Solution solution){
        if (solutionService.isSolutionExist(solution)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        List<Test> testsFail = solution.testSolution();
        if (testsFail.isEmpty()) {
            solution.setResolvedProblem(true);
        } else {
            solution.setTestsFail(testsFail);
        }
        solutionService.createSolution(solution);

        return new ResponseEntity<>(solution, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Solution> updateSolution(@PathVariable("id") Long solutionId,
                                                   @RequestBody Solution solution){
        Solution currentSolution = solutionService.findById(solutionId);
        if (currentSolution == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentSolution.setCode(solution.getCode());
        currentSolution.setInputsOutputs(solution.getInputsOutputs());

        solutionService.updateSolution(currentSolution);
        return new ResponseEntity<>(currentSolution, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Solution> deleteSolution(@PathVariable("id") Long solutionId){
        Solution solution = solutionService.findById(solutionId);
        if (solution == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        solutionService.deleteSolution(solution);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
