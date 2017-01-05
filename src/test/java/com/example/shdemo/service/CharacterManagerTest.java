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

import com.example.shdemo.domain.Character;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CharacterManagerTest {

	@Autowired
	MoviesManager moviesManager;
	
	private final String NAME_1 = "Elsa"; 
	private final String QUALITY_1 = "panowanie nad zima"; 
	private final String TYPE_1 = "Kobieta"; 
	
	private final String NAME_2 = "Krol Julian"; 
	private final String QUALITY_2 = "doskonalosc"; 
	private final String TYPE_2 = "Lemur";	
	
	private final String NAME_3 = "Zygzak McQueen"; 
	private final String QUALITY_3 = "szybkosc"; 
	private final String TYPE_3 = "samochod";	
	
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
		
	}
	
	@Test
	public void checkGetAll(){
		
		Character character1 = new Character(NAME_1,QUALITY_1,TYPE_1);
		Character character2 = new Character(NAME_2,QUALITY_2,TYPE_2);
		
		moviesManager.addCharacter(character1);
		moviesManager.addCharacter(character2);

		List<Character> characters = moviesManager.getAllCharacters();		
		
		Character characterRetrieved = characters.get(0);
		assertEquals(NAME_1, characterRetrieved.getName());
		assertEquals(QUALITY_1, characterRetrieved.getQuality());
		assertEquals(TYPE_1, characterRetrieved.getType());
		
		characterRetrieved = characters.get(1);
		assertEquals(NAME_2, characterRetrieved.getName());
		assertEquals(QUALITY_2, characterRetrieved.getQuality());
		assertEquals(TYPE_2, characterRetrieved.getType());
		
	}
}
