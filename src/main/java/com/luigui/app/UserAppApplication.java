package com.luigui.app;

import java.util.Random;

import com.github.javafaker.Faker;
import com.luigui.app.entities.Role;
import com.luigui.app.entities.User;
import com.luigui.app.entities.UserInRole;
import com.luigui.app.repositories.RoleRepository;
import com.luigui.app.repositories.UserInRoleRepository;
import com.luigui.app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserAppApplication implements ApplicationRunner{

	@Autowired
	private Faker faker;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserInRoleRepository userInRoleRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		Role roles[] = {new Role("ADMIN"),new Role("SUPPORT"),new Role("USER")};
		for (Role role : roles) {
			roleRepository.save(role);
		}
		for(int i = 0; i < 100; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			//user.setProfile(null);
			User created = userRepository.save(user);
			UserInRole userRole = new UserInRole(created,roles[new Random().nextInt(3)]);
			userInRoleRepository.save(userRole);
		}
	}

}
