package com.cognizant.moviecruiser.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException() {
		super("user exists");
	}
}
