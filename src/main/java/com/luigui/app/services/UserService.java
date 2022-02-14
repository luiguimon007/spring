package com.luigui.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;
import com.luigui.app.models.User;

@Service
public class UserService {
	@Autowired
	private Faker faker;
	/**
	 * Código de Servicio
	 */
	private List<User> users = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		for(int i=0; i<100;i++) {
			users.add(new User(faker.funnyName().name(),faker.name().username(),faker.dragonBall().character()));
		}
	}
	
	public List<User> getUsers(String startWith){
		if(startWith == null){
			return users.stream().filter(u -> u.getUsername().startsWith(startWith)).collect(Collectors.toList());
		}else{
			return users;
	
		}
	}
	public User getUserByUsername(String username){
		return users.stream().filter(name -> name.getUsername().equals(username)).findAny().orElseThrow(
			() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not found",username))); 
	}
	public User createUser(User user){
		if(users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))){
			throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("User %s already exists",user.getUsername()));
		}
		users.add(user);
		return user;
	}
	public User updateUser(User user,String username){
		User userToBeUpdated = getUserByUsername(username);
		userToBeUpdated.setNickname(user.getNickname());
		userToBeUpdated.setPassword(user.getPassword());
		userToBeUpdated.setPassword(user.getPassword());
		return userToBeUpdated;
	}
	public void deleteUser(String username){
		User userDelete = getUserByUsername(username);
		users.remove(userDelete);

	}
}
