package com.cognizant.moviecruiser.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.moviecruiser.model.Movie;

public interface MovieRepository extends JpaRepository<Movie , Integer> {
	
	@Query(value = "SELECT u FROM Movie u WHERE u.dateOfLaunch  <= ?1 AND  u.active  = true")
	public List<Movie> getCustomerList(Date date);
}
