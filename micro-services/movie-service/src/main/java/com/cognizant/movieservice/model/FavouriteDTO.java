package com.cognizant.movieservice.model;

import java.util.Set;

public class FavouriteDTO {

	private Set<Movie> movieList;
	private double total;

	public FavouriteDTO(Set<Movie> movieList, double total) {
		super();
		this.movieList = movieList;
		this.total = total;
	}

	public Set<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(Set<Movie> movieList) {
		this.movieList = movieList;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
