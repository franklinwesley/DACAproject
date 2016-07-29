package com.ufcg.controllers;

import com.ufcg.models.Test;
import org.springframework.web.bind.annotation.*;

/**
 * Created by franklin on 29/07/16.
 */
@RestController
//TODO mudar pra /problem/{id}/test
@RequestMapping(value="/test")
public class TestController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String getTests(){
        return "Get Tests";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public String getTest(@PathVariable("id") String testId){
        return "Get Test " + testId;
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public String createTest(@RequestBody Test test){
        return "Create Test";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String updateTest(@PathVariable("id") String testId, @RequestBody Test test){
        return "Update Test " + testId + "\n" + test.toString();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public String deleteTest(@PathVariable("id") String testId){
        return "Delete Test " + testId;
    }
}
