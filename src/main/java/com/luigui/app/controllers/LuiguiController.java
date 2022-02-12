package com.luigui.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //Stereotype
@RequestMapping("/hello")
public class LuiguiController {
	
	@GetMapping
	public String helloWorld() {
		return "Hello World";
	}
}
