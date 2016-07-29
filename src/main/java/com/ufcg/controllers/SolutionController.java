package com.ufcg.controllers;

import com.ufcg.models.Solution;
import org.springframework.web.bind.annotation.*;

/**
 * Created by franklin on 29/07/16.
 */
@RestController
@RequestMapping(value="/problem/{problemId}/solution")
public class SolutionController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String getSolutions(@PathVariable("problemId") String problemaId){
        return "Get Solutions";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public String getSolution(@PathVariable("problemId") String problemaId,
                              @PathVariable("id") String solutionId){
        return "Get Solution " + solutionId;
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public String createSolution(@PathVariable("problemId") String problemaId,
                                 @RequestBody Solution solution){
        return "Create Solution";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String updateSolution(@PathVariable("problemId") String problemaId,
                                 @PathVariable("id") String solutionId, @RequestBody Solution solution){
        return "Update Solution " + solutionId + "\n" + solution.toString();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public String deleteSolution(@PathVariable("problemId") String problemaId,
                                 @PathVariable("id") String solutionId){
        return "Delete Solution " + solutionId;
    }
}
