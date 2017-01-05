package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.*;

import com.example.shdemo.service.MoviesManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class MovieManagerTest {
	
	@Autowired
	MoviesManager moviesManager;
	
	private final String TITLE_1 = "Frozen"; 
	private final String COUNTRY_1 = "USA"; 
	private final String PRODUCTION_1 = "Walt Disney Pictures"; 
	private final int YEAR_1 = 2013;
	
	private final String TITLE_2 = "Madagascar";
	private final String COUNTRY_2 = "USA"; 
	private final String PRODUCTION_2 = "DreamWorks"; 
	private final int YEAR_2 = 2005;
	
	@Test
	public void checkAdd(){
		
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		
		moviesManager.addMovie(movie1);
		moviesManager.addMovie(movie2);
		
		List<Movie> movies = moviesManager.getAllMovies();
		Movie movieRetrieved;
		
		movieRetrieved = moviesManager.getMovie(movies.get(0).getId());		
		assertEquals(TITLE_1, movieRetrieved.getTitle());
		assertEquals(COUNTRY_1, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_1, movieRetrieved.getProduction());
		assertEquals(YEAR_1, movieRetrieved.getYear());
		
		movieRetrieved = moviesManager.getMovie(movies.get(1).getId());		
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
	
	}	
	
}