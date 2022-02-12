package com.luigui.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luigui.app.models.User;
import com.luigui.app.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	@GetMapping()
	//HandlerMethods = Método Http + recurso
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userService.getUsers(),HttpStatus.OK);
	}
	@GetMapping("/{username}")
	public ResponseEntity<User> getUserName(@PathVariable("username") String username){
		return new ResponseEntity<User>(userService.getUserByUsername(username),HttpStatus.OK);
	}
}
