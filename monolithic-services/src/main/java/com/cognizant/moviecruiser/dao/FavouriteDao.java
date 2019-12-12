package com.cognizant.moviecruiser.dao;

import com.cognizant.moviecruiser.exception.FavouriteEmptyException;
import com.cognizant.moviecruiser.model.FavouriteDTO;

public interface FavouriteDao {

	public boolean addFavouriteMovie(int userId, long movieId);

	public FavouriteDTO getAllFavouriteMovies(int userId) throws FavouriteEmptyException;

	public void removeFavouriteMovie(int userId, long movieId);

}
