package com.ufcg.controllers;

import com.ufcg.models.Statistic;
import com.ufcg.services.StatisticService;
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
    StatisticService statisticService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<Statistic> getStatistics(@RequestParam(value = "userId", required = false) Long userId){
        //TODO token?
        int resolvedProblems = statisticService.resolvedProblems();
        int usersSubmittingProblems = statisticService.submittingProblems();

        Statistic statistics = new Statistic(resolvedProblems,usersSubmittingProblems);

        if(userId != null){
            int userResolvedProblems = statisticService.resolvedProblems(userId);
            statistics.setUserResolvedProblems(userResolvedProblems);
        }
        return new ResponseEntity<>(statistics,HttpStatus.OK);
    }
}
