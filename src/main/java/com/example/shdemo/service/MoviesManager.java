package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Character;
import com.example.shdemo.domain.Movie;

public interface MoviesManager {
	
	void addMovie(Movie movie);
	void deleteMovie(Movie movie);
	void editMovie(Movie movieEdit, Movie movieNew);
	Movie getMovie(long id);
	List<Movie> getAllMovies();
	List<Movie> getMovieByCountry(String country);
	List<Character> getAllCharactersFromMovie(Movie movie);
	
	void addCharacter(Character character);
	void deleteCharacter(Character character);
	void editCharacter(Character characterEdit, Character characterNew);
	Character getCharacter(long id);
	List<Character> getAllCharacters();
	List<Character> getCharacterByType(String type);
	
}