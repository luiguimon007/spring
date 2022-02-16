package com.luigui.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luigui.app.models.User;
import com.luigui.app.services.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	@GetMapping()
	//HandlerMethods = Método Http + recurso
	public ResponseEntity<List<User>> getUsers(@RequestParam("startWith") String startWith){
		return new ResponseEntity<List<User>>(userService.getUsers(startWith),HttpStatus.OK);
	}
	/**
	 * 
	 * @param username
	 * @return
	 * localhost:8080/users/emails/10
	 * /users/luigui0305/emails?startDate=19-10-2019&endDate=20-08-2019
	 */
	@GetMapping("/{username}")
	public ResponseEntity<User> getUserName(@PathVariable("username") String username){
		return new ResponseEntity<User>(userService.getUserByUsername(username),HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);
	}
	@PutMapping("/{username}")
	public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("username") String username){
		return new ResponseEntity<User>(userService.updateUser(user,username),HttpStatus.OK);
	}
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
		userService.deleteUser(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
