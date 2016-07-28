package com.ufcg.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by franklin on 28/07/16.
 */
@RestController
@RequestMapping(value="/")
public class IndexController {

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getIndex(){
        return "Server On";
    }
}