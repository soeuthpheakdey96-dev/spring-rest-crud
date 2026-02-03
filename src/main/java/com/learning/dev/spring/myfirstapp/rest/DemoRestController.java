package com.learning.dev.spring.myfirstapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoRestController {
	
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World";
	}
	
	
	@PostMapping("/goodbye")
	public String sayGoodBye() {
		return "Hello World- good bye";
	}

}
