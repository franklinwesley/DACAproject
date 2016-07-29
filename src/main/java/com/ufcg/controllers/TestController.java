package com.ufcg.controllers;

import com.ufcg.models.Test;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/problem/{problemId}/test")
public class TestController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String getTests(@PathVariable("problemId") String problemaId){
        return "Get Tests " + problemaId;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public String getTest(@PathVariable("problemId") String problemaId,
                          @PathVariable("id") String testId){
        return "Get Test " + testId;
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public String createTest(@PathVariable("problemId") String problemaId,
                             @RequestBody Test test){
        return "Create Test";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String updateTest(@PathVariable("problemId") String problemaId,
                             @PathVariable("id") String testId, @RequestBody Test test){
        return "Update Test " + testId + "\n" + test.toString();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public String deleteTest(@PathVariable("problemId") String problemaId,
                             @PathVariable("id") String testId){
        return "Delete Test " + testId;
    }
}
