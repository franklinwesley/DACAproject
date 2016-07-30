package com.ufcg.controllers;

import com.ufcg.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by franklin on 29/07/16.
 */
@RestController
@RequestMapping(value="/statistic")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<Void> getUsers(@RequestParam(value = "userId", required = false) Long userId){
        int resolvedProblems = statisticService.resolvedProblems();
        int usersSubmittingProblems = statisticService.submittingProblems();
        int userResolvedProblems = 0;
        if(userId != null){
            userResolvedProblems = statisticService.resolvedProblems(userId);
        }
        //Retornar esse valores
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
