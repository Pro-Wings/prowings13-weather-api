package com.prowings.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class HelloController {

	@GetMapping("/hello")
	public String greet() {
		log.info("hello method called!!");

		return "hello...!!!";

	}

}
