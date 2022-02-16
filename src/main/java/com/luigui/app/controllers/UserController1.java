package com.luigui.app.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import com.luigui.app.entities.User;
import com.luigui.app.services.UserService1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController1 {
    
    @Autowired
    private UserService1 userService;

    @GetMapping
    @Timed("get.users")
    public ResponseEntity<Page<User>> getUsers(@RequestParam(required = false,value= "page",defaultValue = "0")int page,
    @RequestParam(required= false,value = "size",defaultValue = "500")int size){
        return new ResponseEntity<>(userService.getUsers(page,size),HttpStatus.OK);
    }
    @GetMapping("/usernames")
    public ResponseEntity<Page<String>> getUsersnames(@RequestParam(required = false,value= "page",defaultValue = "0")int page,
    @RequestParam(required = false,value = "size",defaultValue = "500")int size){
        return new ResponseEntity<>(userService.getUsersnames(page, size),HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    @ApiOperation(value = "Returns a user for a given user Id",response = User.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The record was found"),
        @ApiResponse(code = 404, message = "The record  was not found in the database")
    })
    public ResponseEntity<User> getUserById(@PathVariable("userId")Integer userId){
        return new ResponseEntity<User>(userService.getUserById(userId),HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username")String username){
        return new ResponseEntity<User>(userService.getUserByUsername(username),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> authenticate(User user){
        return new ResponseEntity<User>(userService.getUserByUsernameAndPassword(user.getUsername(),user.getPassword()),HttpStatus.OK);
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
