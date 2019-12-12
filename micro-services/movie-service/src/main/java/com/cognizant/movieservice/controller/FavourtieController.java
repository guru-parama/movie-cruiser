package com.cognizant.movieservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.movieservice.dao.UserRepository;
import com.cognizant.movieservice.exception.FavouriteEmptyException;
import com.cognizant.movieservice.model.FavouriteDTO;
import com.cognizant.movieservice.service.FavouriteService;

@RestController
@RequestMapping("/moviecruiser")
@CrossOrigin("*")
public class FavourtieController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FavourtieController.class);
	
	@Autowired
	FavouriteService favouriteService;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/favourites/{userName}")
	public FavouriteDTO getAllFavourtieMovies(@PathVariable String userName) throws FavouriteEmptyException {
		LOGGER.info("Start");
		int userId = userRepository.findByUserName(userName).getId();
		LOGGER.info("End");
		return favouriteService.getAllFavouriteMovie(userId);
	}
	
	@PostMapping("/favourites/{userName}/{movieId}")
	public boolean addFavourtieMovie(@PathVariable String userName,@PathVariable long movieId) {
		LOGGER.info("Start");
		int userId = userRepository.findByUserName(userName).getId();
		LOGGER.info("End");
		return favouriteService.addFavouriteMovie(userId, movieId);
	}
	
	@DeleteMapping("/favourites/{userName}/{movieId}")
	public void removeFavourtieMovie(@PathVariable String userName,@PathVariable long movieId) {
		LOGGER.info("Start");
		int userId = userRepository.findByUserName(userName).getId();
		LOGGER.info("End");
		favouriteService.removeFavouriteMovie(userId, movieId);		
	}
}
