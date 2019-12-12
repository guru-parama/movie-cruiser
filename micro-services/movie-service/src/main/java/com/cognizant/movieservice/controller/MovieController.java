package com.cognizant.movieservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.movieservice.model.Genre;
import com.cognizant.movieservice.model.Movie;
import com.cognizant.movieservice.security.AppUserDetailsService;
import com.cognizant.movieservice.service.MovieService;

@RestController
@RequestMapping("/moviecruiser")
@CrossOrigin("*")
public class MovieController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	MovieService movieService;
	
	@Autowired 
	AppUserDetailsService appUserDetailsService;
	
	@GetMapping("/movies")
	public List<Movie> getAllMovies() {
		LOGGER.info("Start");
		List<Movie> list = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getName();
		Boolean[] arr = {false}; 
		if(!user.equals("anonymousUser")) { 
			appUserDetailsService.loadUserByUsername(user).getAuthorities().forEach(auth -> {
				if(auth.getAuthority().equals("admin")) {
					arr[0] = true;
				} else {
					arr[0] = false;
				}
			});
			if(arr[0] == true)
				list = (ArrayList<Movie>) movieService.getAllMovieListAdmin();
			else  {
				list = movieService.getAllMovieListCustomer();
			}
		} else {
			list = movieService.getAllMovieListCustomer();
		}
		LOGGER.info("End");
		return list;
	}
	
	@GetMapping("/movies/{id}")
	public Movie getMovie(@PathVariable long id) {
		LOGGER.info("Start");
		LOGGER.info("End");
		return movieService.getMovie(id);
	}
	
	@PutMapping("/movies")
	public void modifyMovie(@RequestBody Movie movie ) {
		LOGGER.info("Start");
		LOGGER.info("End");
		movieService.modifyMovie(movie);
	}
	
	@GetMapping("/genre")
	public List<Genre> getAllGenre() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return movieService.getGenreList();
	}
}
