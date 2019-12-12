package com.cognizant.moviecruiser.testing;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.moviecruiser.controller.MovieController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieCruiserApplicationTesting {

	@Autowired
	private MovieController movieController;
	
	@Test
	public void contextLoads() {
		assertNotNull(movieController);
	}
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void getAllMovies() throws Exception{
		ResultActions actions = mvc.perform(get("/moviecruiser/movies"));
		actions.andExpect(status().isOk());
	}
}
