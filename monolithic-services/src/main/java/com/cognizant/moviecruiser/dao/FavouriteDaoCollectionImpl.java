package com.cognizant.moviecruiser.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cognizant.moviecruiser.exception.FavouriteEmptyException;
import com.cognizant.moviecruiser.model.FavouriteDTO;
import com.cognizant.moviecruiser.model.Movie;

@Repository
public class FavouriteDaoCollectionImpl implements FavouriteDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FavouriteDaoCollectionImpl.class);
	private static HashMap<String, FavouriteDTO> userFavourites = null;

	public FavouriteDaoCollectionImpl() {
		if (userFavourites == null) {
			userFavourites = new HashMap<String, FavouriteDTO>();
		}
	}

	public boolean addFavouriteMovie(String userId, long movieId) {
		LOGGER.info("Start");
		boolean cartFalg = false;
		MovieDao movieDao = new MovieDaoCollectionImpl();
		Movie movie = movieDao.getMovie(movieId);
		FavouriteDTO favorite;
		List<Movie> movieList;
		if (userFavourites.containsKey(userId)) {
			favorite = userFavourites.get(userId);
			movieList = favorite.getMovieList();
			if(!movieList.contains(movie))
				movieList.add(movie);
			else
				cartFalg = true;
			favorite.setMovieList(movieList);
			userFavourites.put(userId, favorite);
		} else {
			movieList = new ArrayList<Movie>();
			if(!movieList.contains(movie))
				movieList.add(movie);
			else
				cartFalg = true;
			favorite = new FavouriteDTO(movieList, 0);
			userFavourites.put(userId, favorite);
		}
		LOGGER.info("End");
		return cartFalg;
	}

	public FavouriteDTO getAllFavouriteMovies(String userId) throws FavouriteEmptyException {
		LOGGER.info("Start");
		if (!userFavourites.containsKey(userId)) {
			throw new FavouriteEmptyException();
		}
		FavouriteDTO favorite = userFavourites.get(userId);
		List<Movie> movieList = favorite.getMovieList();
		int sum = 0;
		if (movieList.isEmpty()) {
			throw new FavouriteEmptyException();
		}
		else {
			sum = favorite.getMovieList().size();
		}
		favorite.setTotal(sum);
		LOGGER.info("End");
		return favorite;
	}

	public void removeFavouriteMovie(String userId, long movieId) {
		LOGGER.info("Start");
		FavouriteDTO favorite = userFavourites.get(userId);
		List<Movie> movieList = favorite.getMovieList();
		for (int i = 0; i < movieList.size(); i++) {
			if (movieId == movieList.get(i).getId()) {
				movieList.remove(i);
			}
		}
		LOGGER.info("End");
	}
}
