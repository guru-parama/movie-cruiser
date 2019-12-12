package com.cognizant.moviecruiser.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruiser.dao.GenreRepository;
import com.cognizant.moviecruiser.dao.MovieRepository;
import com.cognizant.moviecruiser.model.Genre;
import com.cognizant.moviecruiser.model.Movie;

@Service
public class MovieService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	GenreRepository genreRepository; 
	
	public List<Movie> getAllMovieListAdmin(){
		LOGGER.info("Start");
		LOGGER.info("End");
		return movieRepository.findAll();
	}
	public List<Movie> getAllMovieListCustomer(){
		LOGGER.info("Start");
		LOGGER.info("End");
		return movieRepository.getCustomerList(new Date());
	}
	public Movie getMovie(long id) {
		LOGGER.info("Start");
		LOGGER.info("End");
		return movieRepository.findById((int)id).get();
	}
	public void modifyMovie(Movie movie) {
		LOGGER.info("Start");
		LOGGER.info("End");
		movieRepository.save(movie);
	}
	public List<Genre> getGenreList() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return genreRepository.findAll();
	}
}
