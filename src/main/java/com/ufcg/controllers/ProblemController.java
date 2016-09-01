package com.ufcg.controllers;

import com.ufcg.models.Problem;
import com.ufcg.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value="/problem")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity getProblems(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "sort", defaultValue = "date") String sort,
                                                               @RequestParam(value = "user", required = false) Long user){
        //TODO token?
        Page<Problem> problems = problemService.findAllProblems(page,sort,user);
        if(problems.getNumber() == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(problems, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Problem> getProblem(@PathVariable("id") Long problemId){
        Problem problem = problemService.findById(problemId);
        if(problem == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Void> createProblem(@RequestBody Problem problem, UriComponentsBuilder ucBuilder){
        if (problemService.isProblemExist(problem)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        problemService.createProblem(problem);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/problem/{id}").buildAndExpand(problem.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Problem> updateProblem(@PathVariable("id") Long problemId,
                                                 @RequestBody Problem problem){
        Problem currentProblem = problemService.findById(problemId);
        if (currentProblem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentProblem.setName(problem.getName());
        currentProblem.setDescription(problem.getDescription());
        currentProblem.setTip(problem.getTip());
        currentProblem.setType(problem.getType());
        currentProblem.setTests(problem.getTests());

        problemService.updateProblem(currentProblem);
        return new ResponseEntity<>(currentProblem, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Problem> deleteProblem(@PathVariable("id") Long problemId){
        Problem problem = problemService.findById(problemId);
        if (problem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        problemService.deleteProblem(problem);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PATCH)
    public ResponseEntity<Void> publishProblem(@PathVariable("id") Long problemId){
        Problem problem = problemService.findById(problemId);
        if (problem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean publishedProblem = problemService.publishProblem(problem);
        if (publishedProblem) {
            return new ResponseEntity<>(HttpStatus.GONE);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}