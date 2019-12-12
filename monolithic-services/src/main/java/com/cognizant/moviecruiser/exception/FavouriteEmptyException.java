package com.cognizant.moviecruiser.exception;

public class FavouriteEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	public FavouriteEmptyException() {
		super("favorites empty");
	}
}
