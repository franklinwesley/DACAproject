package com.ufcg.controllers;

import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.Test;
import com.ufcg.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            users.add(new User("Email " + i, "Password", (long) i));
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public String getUser(@PathVariable("id") String userId){
        return "Get User " + userId;
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public String createUser(@RequestBody User user){
        return "Create user";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String updateUser(@PathVariable("id") String userId, @RequestBody User user){
        return "Update Problem " + userId + "\n" + user.toString();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") String userId){
        return "Delete User " + userId;
    }

    @RequestMapping(value="/{id}/problem", method= RequestMethod.GET)
    public ResponseEntity<List<Problem>> getUserProblems(@PathVariable("id") String userId){

        List<Problem> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new Problem((long) i, "Problem " + i, "description", "tip", new ArrayList<>(), Visibility.PRIVATE));
        }
        //Lista de problemas

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}/statistic", method= RequestMethod.GET)
    public String getUserStatistic(@PathVariable("id") String userId){
        return "Get User Statistic" + userId;
    }
}
