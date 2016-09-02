package com.ufcg.controllers;

import com.ufcg.services.SolutionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/statistic")
public class StatisticController {

    @Autowired
    SolutionService solutionService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<JSONObject> getStatistics(@RequestParam(value = "userId", required = false) Long userId){
        //TODO token?
        int problemsResolved = solutionService.problemsResolved();
        int usersSubmittingProblems = solutionService.userSubmitting();

        JSONObject response = new JSONObject();
        response.put("problemsResolved",problemsResolved);
        response.put("usersSubmittingProblems",usersSubmittingProblems);

        if(userId != null){
            int userResolvedProblems = solutionService.userProblemsResolved(userId);
            response.put("userResolvedProblems",userResolvedProblems);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
