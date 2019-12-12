package com.cognizant.userauthservice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.userauthservice.exception.UserAlreadyExistsException;
import com.cognizant.userauthservice.model.Users;
import com.cognizant.userauthservice.security.AppUserDetailsService;


@RestController
@CrossOrigin("*")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	PasswordEncoder passwordEncoder; 

	@Autowired
	AppUserDetailsService appUserDetailsService; 
	@PostMapping("/signup")
	public void signup(@RequestBody @Valid Users user) throws UserAlreadyExistsException {
	
		LOGGER.info("Start");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		appUserDetailsService.signup(user);
		LOGGER.info("End");
	}
	 
		@GetMapping("/user/{userName}")
	public UserDetails findByUserName(@PathVariable String userName) {
		//return userService.findUserByName(userName);
		return appUserDetailsService.loadUserByUsername(userName);
	}
	
}
