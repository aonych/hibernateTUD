package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.*;
import com.example.shdemo.domain.Character;
import com.example.shdemo.service.MoviesManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class MovieManagerTest {
	
	@Autowired
	MoviesManager moviesManager;
	
	public static int nr = -1;	
	
	private final String TITLE_1 = "Frozen"; 
	private final String COUNTRY_1 = "USA"; 
	private final String PRODUCTION_1 = "Walt Disney Pictures"; 
	private final int YEAR_1 = 2013;
	
	private final String TITLE_2 = "Asterix i Kleopatra";
	private final String COUNTRY_2 = "Francja"; 
	private final String PRODUCTION_2 = "Raymond Leblanc"; 
	private final int YEAR_2 = 1968;
	
	private final String TITLE_3 = "Franklin i skarb jeziora";
	private final String COUNTRY_3 = "Francja"; 
	private final String PRODUCTION_3 = "Mars Distribution"; 
	private final int YEAR_3 = 2006;
	
	private final String TITLE_4 = "Madagascar";
	private final String COUNTRY_4 = "USA"; 
	private final String PRODUCTION_4 = "DreamWorks"; 
	private final int YEAR_4 = 2005;
	
	private final String NAME_1 = "Elsa"; 
	private final String QUALITY_1 = "panowanie nad zima"; 
	private final String TYPE_1 = "kobieta"; 
	
	private final String NAME_2 = "Olaf"; 
	private final String QUALITY_2 = "umiejetnosc mowienia"; 
	private final String TYPE_2 = "balwan";	
	
	private final String NAME_3 = "Kowalski"; 
	private final String QUALITY_3 = "inteligencja"; 
	private final String TYPE_3 = "pingwin";
	
	@Before
	public void BInfo() {
		String[] names = new String[6];
		names[0] = "dodawania";
		names[1] = "usuwania";
		names[2] = "edycji";
		names[3] = "pobierania wszystkich rekordow";
		names[4] = "pobierania po kraju";
		names[5] = "usuwania z postaciami";
		
		System.out.println("Rozpoczęcie testu "+ names[++nr]+ "\n");
	}
	
	@After
	public void AInfo() {
		String[] names = new String[6];
		names[0] = "dodawania";
		names[1] = "usuwania";
		names[2] = "edycji";
		names[3] = "pobierania wszystkich rekordow";
		names[4] = "pobierania po kraju";
		names[5] = "usuwania z postaciami";

		System.out.println("Zakończenie testu "+ names[nr] +"\n");
	}
	
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
	
	@Test
	public void checkDelete(){
		
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		
		moviesManager.addMovie(movie1);
		moviesManager.addMovie(movie2);
		
		int count = moviesManager.getAllMovies().size();
		
		moviesManager.deleteMovie(movie1);
		
		List<Movie> movies = moviesManager.getAllMovies();
	
		Movie movieRetrieved = moviesManager.getMovie(movies.get(0).getId());	
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
		assertEquals(count-1, movies.size());
		
	}
	
	@Test
	public void checkEdit(){
		
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		Movie movie_edit = new Movie(TITLE_3, COUNTRY_3,PRODUCTION_3,YEAR_3);
		
		moviesManager.addMovie(movie1);
		moviesManager.addMovie(movie2);
		moviesManager.editMovie(movie1, movie_edit);
		
		List<Movie> movies = moviesManager.getAllMovies();
		
		Movie movieRetrieved = moviesManager.getMovie(movies.get(0).getId());
		assertEquals(TITLE_3, movieRetrieved.getTitle());
		assertEquals(COUNTRY_3, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_3, movieRetrieved.getProduction());
		assertEquals(YEAR_3, movieRetrieved.getYear());
		
		movieRetrieved = moviesManager.getMovie(movies.get(1).getId());
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
		
	}
	
	@Test
	public void checkGetAll(){
		
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		
		moviesManager.addMovie(movie1);
		moviesManager.addMovie(movie2);
		
		List<Movie> movies = moviesManager.getAllMovies();		
		
		Movie movieRetrieved = movies.get(0);
		assertEquals(TITLE_1, movieRetrieved.getTitle());
		assertEquals(COUNTRY_1, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_1, movieRetrieved.getProduction());
		assertEquals(YEAR_1, movieRetrieved.getYear());
		
		movieRetrieved = movies.get(1);
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
		
	}
	
	@Test
	public void checkGetByCountry(){
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		Movie movie3 = new Movie(TITLE_3, COUNTRY_3,PRODUCTION_3,YEAR_3);
		Movie movie4 = new Movie(TITLE_4, COUNTRY_4,PRODUCTION_4,YEAR_4);
		
		moviesManager.addMovie(movie1);
		moviesManager.addMovie(movie2);
		moviesManager.addMovie(movie3);
		moviesManager.addMovie(movie4);
		
		List<Movie> movies = moviesManager.getMovieByCountry(COUNTRY_2);
		int count = moviesManager.getMovieByCountry(COUNTRY_2).size();
		
		Movie movieRetrieved;
		assertEquals(count,2);
		
		movieRetrieved = moviesManager.getMovie(movies.get(0).getId());
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
		
		movieRetrieved = moviesManager.getMovie(movies.get(1).getId());
		assertEquals(TITLE_3, movieRetrieved.getTitle());
		assertEquals(COUNTRY_3, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_3, movieRetrieved.getProduction());
		assertEquals(YEAR_3, movieRetrieved.getYear());
	}
	
	@Test
	public void checkDeleteWithCharacters() {
		
		Character character1 = new Character(NAME_1,QUALITY_1,TYPE_1);
		Character character2 = new Character(NAME_2,QUALITY_2,TYPE_2);
		Character character3 = new Character(NAME_3,QUALITY_3,TYPE_3);
		
		
		moviesManager.addCharacter(character1);
		moviesManager.addCharacter(character2);
		List<Character> characters = moviesManager.getAllCharacters();		
		
		moviesManager.addCharacter(character3);
		List<Character> charactersMadagaskar = Arrays.asList(moviesManager.getAllCharacters().get(2));	
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1,characters);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2,charactersMadagaskar);		
		moviesManager.addMovie(movie1);
		moviesManager.addMovie(movie2);
		
		int countCharacters = moviesManager.getAllCharacters().size();
		int countMovies = moviesManager.getAllMovies().size();
		
		moviesManager.deleteMovie(movie1);
		
		List<Movie> movies = moviesManager.getAllMovies();
		
		Movie movieRetrieved = moviesManager.getMovie(movies.get(0).getId());	
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
		assertEquals(countMovies-1, movies.size());
		
		characters = moviesManager.getAllCharacters();
		
		Character characterRetrieved = moviesManager.getCharacter(characters.get(0).getId());
		assertEquals(NAME_3,characterRetrieved.getName());
		assertEquals(QUALITY_3,characterRetrieved.getQuality());
		assertEquals(TYPE_3,characterRetrieved.getType());
		assertEquals(countCharacters-2, characters.size());
		
		
	}
	
	
	
}