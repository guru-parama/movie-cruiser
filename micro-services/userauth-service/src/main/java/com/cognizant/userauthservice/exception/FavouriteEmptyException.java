package com.cognizant.userauthservice.exception;

public class FavouriteEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	public FavouriteEmptyException() {
		super("favorites empty");
	}
}
