package com.cognizant.moviecruiser.service;

import java.awt.CardLayout;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecruiser.dao.FavouriteDao;
import com.cognizant.moviecruiser.dao.MovieRepository;
import com.cognizant.moviecruiser.dao.UserRepository;
import com.cognizant.moviecruiser.exception.FavouriteEmptyException;
import com.cognizant.moviecruiser.model.FavouriteDTO;
import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.model.Users;

@Service
public class FavouriteService {

private static final Logger LOGGER = LoggerFactory.getLogger(FavouriteService.class);
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public FavouriteDTO getAllFavouriteMovie(int userId) throws FavouriteEmptyException {
		LOGGER.info("Start");
		Set<Movie> menuItemList = userRepository
											.findByUserName(userRepository
													.findById(userId)
														.get()
														.getUserName()).getMovieList();
		if(menuItemList.size() == 0) {
			throw new FavouriteEmptyException();
		}
		double total = userRepository.getFavTotal(userId);
		FavouriteDTO cartDTO = new FavouriteDTO(menuItemList, total);
		 
		LOGGER.info("End");
		return cartDTO;
	}
	
	public boolean addFavouriteMovie(int userId, long movieId) {
		LOGGER.info("Start");
		boolean favFlag = false;
		Users user = userRepository.findById(userId).get();
		Movie movie = movieRepository.findById((int) movieId).get();
		Set<Movie> menuItemList = user.getMovieList();
		if(!menuItemList.contains(movie)) {
			menuItemList.add(movie);
		}
		else {
			favFlag = true;
		}
		user.setMovieList(menuItemList);		
		userRepository.save(user);
		LOGGER.info("End");
		return favFlag;		
	}
	
	public void removeFavouriteMovie(int userId, long movieId) {
		LOGGER.info("Start");
		Users user = userRepository.findById(userId).get();
		Movie item = movieRepository.findById((int) movieId).get();
		Set<Movie> menuItemList = user.getMovieList();
		menuItemList.remove(item);
		userRepository.save(user);
		LOGGER.info("End");	
	}
}
