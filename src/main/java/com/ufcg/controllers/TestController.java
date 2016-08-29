package com.ufcg.controllers;

import com.ufcg.models.Test;
import com.ufcg.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value="/problem/{problemId}/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<List<Test>> getTests(@PathVariable("problemId") Long problemId){
        List<Test> tests = testService.findAllTestsOfProblem(problemId);
        if(tests.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Test> getTest(@PathVariable("id") Long testId){
        Test test = testService.findById(testId);
        if(test == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Void> createTest(@RequestBody Test test,
                                           UriComponentsBuilder ucBuilder){
        if (testService.isTestExist(test)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        testService.createTest(test);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/problem/{problemId}/test/{id}").buildAndExpand(test.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Test> updateTest(@PathVariable("id") Long testId,
                                           @RequestBody Test test){
        Test currentTest = testService.findById(testId);
        if (currentTest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentTest.setName(test.getName());
        currentTest.setTip(test.getTip());
        currentTest.setType(test.getType());
        currentTest.setInputsOutputs(test.getInputsOutputs());

        testService.updateTest(currentTest);
        return new ResponseEntity<>(currentTest, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Test> deleteTest(@PathVariable("id") Long testId){
        Test test = testService.findById(testId);
        if (test == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        testService.deleteTest(test);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
