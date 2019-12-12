package com.cognizant.movieservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.movieservice.model.Genre;

public interface GenreRepository extends JpaRepository< Genre, Integer> {

}
