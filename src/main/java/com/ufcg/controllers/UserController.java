package com.ufcg.controllers;

import com.ufcg.models.User;
import com.ufcg.models.UserDTO;
import com.ufcg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> users = userService.findAllUser();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long userId){
        UserDTO user = userService.findById(userId);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user,
                                           UriComponentsBuilder ucBuilder){

        User currentUser = new User(user.getEmail(), user.getPassword(), user.getType());

        if (userService.findByEmail(currentUser)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.createUser(currentUser);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId, @RequestBody User user){
        UserDTO currentUser = userService.findById(userId);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (Objects.equals(currentUser.getId(), userId)) {
            userService.updateUser(user);
            currentUser = new UserDTO(user);
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long userId){
        UserDTO user = userService.findById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(user.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
