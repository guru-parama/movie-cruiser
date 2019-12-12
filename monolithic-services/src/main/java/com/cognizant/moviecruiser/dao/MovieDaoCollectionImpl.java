package com.cognizant.moviecruiser.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.service.MovieService;

@Repository
public class MovieDaoCollectionImpl implements MovieDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieDaoCollectionImpl.class);
	private static List<Movie> movieList;

	@SuppressWarnings("unchecked")
	public MovieDaoCollectionImpl() {
		LOGGER.info("Start");
		if (movieList == null) {
			@SuppressWarnings("resource")
			ApplicationContext factory = new ClassPathXmlApplicationContext("moviecruiser.xml");
			movieList = (List<Movie>) factory.getBean("movieList");
		}
		LOGGER.info("End");
	}

	public List<Movie> getMovieListAdmin() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return movieList;
	}

	public List<Movie> getMovieListCustomer() {
		LOGGER.info("Start");
		List<Movie> movieListCustomer = new ArrayList<Movie>();
		Date dateNow = new Date();
		for (Movie movieTest : movieList) {
			if (dateNow.equals(movieTest.getDateOfLaunch()) || movieTest.getDateOfLaunch().before(dateNow)) {
				if (movieTest.isActive() == true) {
					movieListCustomer.add(movieTest);
				}
			}
		}
		LOGGER.info("End");
		return movieListCustomer;
	}

	public void modifyMovie(Movie movie) {
		LOGGER.info("Start");
		if (movie != null) {
			for (int i = 0; i < movieList.size(); i++) {
				Movie movieTest = movieList.get(i);
				if (movie.equals(movieTest)) {						
					movieList.get(i).setName(movie.getName());
					movieList.get(i).setBoxOffice(movie.getBoxOffice());
					movieList.get(i).setDateOfLaunch(movie.getDateOfLaunch());
					movieList.get(i).setGenre(movie.getGenre());
					movieList.get(i).setActive(movie.isActive());
					movieList.get(i).setHasTeaser(movie.isHasTeaser());
				}
			}
		}
		LOGGER.info("End");
	}

	public Movie getMovie(long movieId) {
		LOGGER.info("Start");
		Movie requestedItem = null;
		for (Movie movieTest : movieList) {
			if (movieTest.getId() == movieId) {
				requestedItem = movieTest;
			}
		}
		LOGGER.info("End");
		return requestedItem;
	}

}
