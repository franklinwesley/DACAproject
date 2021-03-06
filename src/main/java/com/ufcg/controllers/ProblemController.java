package com.ufcg.controllers;

import com.ufcg.models.Problem;
import com.ufcg.models.ProblemDTO;
import com.ufcg.services.ProblemService;
import com.ufcg.services.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/problem")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @Autowired
    SolutionService solutionService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity getProblems(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "100") int size,
                                      @RequestParam(value = "sort", defaultValue = "date") String sort,
                                      @RequestParam(value = "user", required = false) Long user){

        Page<Problem> problems = problemService.findAllProblems(page,size,sort);
        if(problems.getNumberOfElements() == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (user != null) {
            List<Problem> problemsResolved = solutionService.userProblemsResolved(user);
            for (Problem problemResolved: problemsResolved) {
                int index = problems.getContent().indexOf(problemResolved);
                problems.getContent().get(index).setResolved(true);
            }
        }

        List<ProblemDTO> problemDTOs = new ArrayList<>();

        for (Problem problem: problems) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String date = problem.getDate().format(formatter);
            problemDTOs.add(new ProblemDTO(problem.getId(), problem.getName(), problem.getDescription(), date, problem.isResolved()));
        }

        Page<ProblemDTO> problemDTOPage = new PageImpl(problemDTOs);

        return new ResponseEntity<>(problemDTOPage, HttpStatus.OK);
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

        problemService.updateProblem(problemId, currentProblem);
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
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}