package com.ufcg.controllers;

import com.ufcg.models.Test;
import com.ufcg.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Test> getTest(@PathVariable("id") Long testId){
        Test test = testService.findById(testId);
        if(test == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Void> createTest(@PathVariable("problemId") Long problemId,
                                           @RequestBody Test test,
                                           UriComponentsBuilder ucBuilder){
        if (!testService.isProblemExist(problemId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        testService.createTest(problemId,test);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/problem/{problemId}/test/{id}").buildAndExpand(problemId,test.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Test> updateTest(@PathVariable("problemId") Long problemId,
                                           @PathVariable("id") Long testId,
                                           @RequestBody Test test){
        Test currentTest = testService.findById(testId);
        if (currentTest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentTest.setName(test.getName());
        currentTest.setTip(test.getTip());
        currentTest.setType(test.getType());
        currentTest.setInput(test.getInput());
        currentTest.setOutput(test.getOutput());

        testService.updateTest(problemId,currentTest);
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
