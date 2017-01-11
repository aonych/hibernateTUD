package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Character;
import com.example.shdemo.domain.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CharacterManagerTest {

	@Autowired
	MoviesManager moviesManager;
	
	private final String NAME_1 = "Elsa"; 
	private final String QUALITY_1 = "panowanie nad zima"; 
	private final String TYPE_1 = "kobieta"; 
	
	private final String NAME_2 = "Olaf"; 
	private final String QUALITY_2 = "umiejetnosc mowienia"; 
	private final String TYPE_2 = "balwan";	
	
	private final String NAME_3 = "Bella"; 
	private final String QUALITY_3 = "inteligencja"; 
	private final String TYPE_3 = "kobieta";
	
	private final String NAME_4 = "Kowalski"; 
	private final String QUALITY_4 = "inteligencja"; 
	private final String TYPE_4 = "pingwin";
	
	private final String TITLE_1 = "Frozen"; 
	private final String COUNTRY_1 = "USA"; 
	private final String PRODUCTION_1 = "Walt Disney Pictures"; 
	private final int YEAR_1 = 2013;
	
	private final String TITLE_2 = "Madagascar";
	private final String COUNTRY_2 = "USA"; 
	private final String PRODUCTION_2 = "DreamWorks"; 
	private final int YEAR_2 = 2005;
	
	@Test
	public void checkAdd() {

		Character character1 = new Character(NAME_1,QUALITY_1,TYPE_1);
		Character character2 = new Character(NAME_2,QUALITY_2,TYPE_2);
		
		moviesManager.addCharacter(character1);
		moviesManager.addCharacter(character2);

		List<Character> characters = moviesManager.getAllCharacters();
		Character characterRetrieved;
		
		characterRetrieved = moviesManager.getCharacter(characters.get(0).getId());		
		assertEquals(NAME_1, characterRetrieved.getName());
		assertEquals(QUALITY_1, characterRetrieved.getQuality());
		assertEquals(TYPE_1, characterRetrieved.getType());
		
		characterRetrieved = moviesManager.getCharacter(characters.get(1).getId());		
		assertEquals(NAME_2, characterRetrieved.getName());
		assertEquals(QUALITY_2, characterRetrieved.getQuality());
		assertEquals(TYPE_2, characterRetrieved.getType());

	}
	
	@Test
	public void checkDelete(){
		
		Character character1 = new Character(NAME_1, QUALITY_1, TYPE_1);
		Character character2 = new Character(NAME_2, QUALITY_2, TYPE_2);
		
		moviesManager.addCharacter(character1);
		moviesManager.addCharacter(character2);
		
		int count = moviesManager.getAllCharacters().size();
		
		moviesManager.deleteCharacter(character1);
		
		List<Character> characters = moviesManager.getAllCharacters();
		
		Character characterRetrieved = moviesManager.getCharacter(characters.get(0).getId());
		assertEquals(NAME_2, characterRetrieved.getName());
		assertEquals(QUALITY_2, characterRetrieved.getQuality());
		assertEquals(TYPE_2, characterRetrieved.getType());
		assertEquals(count-1, characters.size());
	}
	
	@Test
	public void checkEdit(){
		
		Character character1 = new Character(NAME_1, QUALITY_1, TYPE_1);
		Character character2 = new Character(NAME_2, QUALITY_2, TYPE_2);
		Character character_edit = new Character(NAME_3, QUALITY_3, TYPE_3);
		
		moviesManager.addCharacter(character1);
		moviesManager.addCharacter(character2);
		moviesManager.editCharacter(character1, character_edit);
		
		List<Character> characters = moviesManager.getAllCharacters();
		
		Character characterRetrieved = moviesManager.getCharacter(characters.get(0).getId());
		assertEquals(NAME_3, characterRetrieved.getName());
		assertEquals(QUALITY_3, characterRetrieved.getQuality());
		assertEquals(TYPE_3, characterRetrieved.getType());
		
		characterRetrieved = moviesManager.getCharacter(characters.get(1).getId());
		assertEquals(NAME_2, characterRetrieved.getName());
		assertEquals(QUALITY_2, characterRetrieved.getQuality());
		assertEquals(TYPE_2, characterRetrieved.getType());
		
	}
	
	@Test
	public void checkGetAll(){
		
		Character character1 = new Character(NAME_1,QUALITY_1,TYPE_1);
		Character character2 = new Character(NAME_2,QUALITY_2,TYPE_2);
		
		moviesManager.addCharacter(character1);
		moviesManager.addCharacter(character2);

		List<Character> characters = moviesManager.getAllCharacters();		
		int count = moviesManager.getAllCharacters().size();
		
		Character characterRetrieved = characters.get(0);
		assertEquals(NAME_1, characterRetrieved.getName());
		assertEquals(QUALITY_1, characterRetrieved.getQuality());
		assertEquals(TYPE_1, characterRetrieved.getType());
		
		characterRetrieved = characters.get(1);
		assertEquals(NAME_2, characterRetrieved.getName());
		assertEquals(QUALITY_2, characterRetrieved.getQuality());
		assertEquals(TYPE_2, characterRetrieved.getType());
		
		assertEquals(count, 2);
		
	}
	
	@Test
	public void checkGetByCountry(){
		
		Character character1 = new Character(NAME_1,QUALITY_1,TYPE_1);
		Character character2 = new Character(NAME_2,QUALITY_2,TYPE_2);
		Character character3 = new Character(NAME_3,QUALITY_3,TYPE_3);
		
		moviesManager.addCharacter(character1);
		moviesManager.addCharacter(character2);
		moviesManager.addCharacter(character3);
		
		List<Character> characters = moviesManager.getCharacterByType(TYPE_1);
		int count = moviesManager.getCharacterByType(TYPE_1).size();
		
		Character characterRetrieved;
		assertEquals(count,2);
		
		characterRetrieved = moviesManager.getCharacter(characters.get(0).getId());
		assertEquals(NAME_1, characterRetrieved.getName());
		assertEquals(QUALITY_1, characterRetrieved.getQuality());
		assertEquals(TYPE_1, characterRetrieved.getType());
		
		characterRetrieved = moviesManager.getCharacter(characters.get(1).getId());
		assertEquals(NAME_3, characterRetrieved.getName());
		assertEquals(QUALITY_3, characterRetrieved.getQuality());
		assertEquals(TYPE_3, characterRetrieved.getType());
	}
	
	@Test
	public void checkGetCharactersByMovie() {
		
		Character character1 = new Character(NAME_1,QUALITY_1,TYPE_1);
		Character character2 = new Character(NAME_2,QUALITY_2,TYPE_2);
		Character character3 = new Character(NAME_4,QUALITY_4,TYPE_4);
		
		
		moviesManager.addCharacter(character1);
		moviesManager.addCharacter(character2);
		List<Character> characters = moviesManager.getAllCharacters();		
		
		moviesManager.addCharacter(character3);
		List<Character> charactersMadagaskar = Arrays.asList(moviesManager.getAllCharacters().get(2));	
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1,characters);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2,charactersMadagaskar);		
		moviesManager.addMovie(movie1);
		moviesManager.addMovie(movie2);
		
		characters = moviesManager.getAllCharactersFromMovie(movie1);
		int count = moviesManager.getAllCharactersFromMovie(movie1).size();
		
		Character characterRetrieved = characters.get(0);
		assertEquals(NAME_1,characterRetrieved.getName());
		assertEquals(QUALITY_1,characterRetrieved.getQuality());
		assertEquals(TYPE_1,characterRetrieved.getType());
		characterRetrieved = characters.get(1);
		assertEquals(NAME_2,characterRetrieved.getName());
		assertEquals(QUALITY_2,characterRetrieved.getQuality());
		assertEquals(TYPE_2,characterRetrieved.getType());
		assertEquals(TYPE_2,characterRetrieved.getType());
		assertEquals(count,2);
		
		charactersMadagaskar = moviesManager.getAllCharactersFromMovie(movie2);
		int count2 = moviesManager.getAllCharactersFromMovie(movie2).size();
		
		characterRetrieved = charactersMadagaskar.get(0);
		assertEquals(NAME_4,characterRetrieved.getName());
		assertEquals(QUALITY_4,characterRetrieved.getQuality());
		assertEquals(TYPE_4,characterRetrieved.getType());
		assertEquals(count2,1);
		
	}
}
