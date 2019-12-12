package com.cognizant.moviecruiser.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.moviecruiser.model.Genre;

public interface GenreRepository extends JpaRepository< Genre, Integer> {

}
