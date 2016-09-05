package com.ufcg.controllers;

import com.ufcg.models.Statistic;
import com.ufcg.services.SolutionService;
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
    public ResponseEntity<Statistic> getStatistics(@RequestParam(value = "userId", required = false) Long userId){
        //TODO token?
        int problemsResolved = solutionService.problemsResolved();
        int usersSubmittingProblems = solutionService.userSubmitting();

        Statistic statistics = new Statistic(problemsResolved, usersSubmittingProblems);

        if(userId != null){
            int userResolvedProblems = solutionService.userProblemsResolvedNumber(userId);
            statistics.setUserResolvedProblems(userResolvedProblems);
        }
        return new ResponseEntity<>(statistics,HttpStatus.OK);
    }
}
