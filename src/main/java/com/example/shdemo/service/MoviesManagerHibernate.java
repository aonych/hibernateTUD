package com.example.shdemo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Movie;
import com.example.shdemo.domain.Character;

@Component
@Transactional
public class MoviesManagerHibernate implements MoviesManager {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addMovie(Movie movie) {
		movie.setId(0);
		sessionFactory.getCurrentSession().persist(movie);
	}
	
	@Override
	public void deleteMovie(Movie movie) {
		movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class,movie.getId());
		sessionFactory.getCurrentSession().delete(movie);
	}
	
	@Override
	public void editMovie(Movie movieEdit, Movie movieNew) {
		movieEdit.setTitle(movieNew.getTitle());
		movieEdit.setProduction(movieNew.getProduction());
		movieEdit.setCountry(movieNew.getCountry());
		movieEdit.setYear(movieNew.getYear());
		movieEdit.setCharacters(movieNew.getCharacters());
		sessionFactory.getCurrentSession().update(movieEdit);
	}
	
	@Override
	public Movie getMovie(long id) {
		return (Movie) sessionFactory.getCurrentSession().getNamedQuery("Movie.one").setLong("id", id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getAllMovies() {
		return sessionFactory.getCurrentSession().getNamedQuery("Movie.all").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMovieByCountry(String country) {
		return sessionFactory.getCurrentSession().getNamedQuery("Movie.byCountry").setString("country", country).list();
	}
	
	@Override
	public List<Character> getAllCharactersFromMovie (Movie movie) {
		return movie.getCharacters();
	}
	
	
	@Override
	public void addCharacter(Character character) {
		character.setId(0);
		sessionFactory.getCurrentSession().persist(character);
	}
	
	@Override
	public void deleteCharacter(Character character) {
			sessionFactory.getCurrentSession().delete(character);
	}
	
	@Override
	public void editCharacter(Character characterEdit, Character characterNew) {
			characterEdit.setName(characterNew.getName());
			characterEdit.setQuality(characterNew.getQuality());
			characterEdit.setType(characterNew.getType());
			sessionFactory.getCurrentSession().update(characterEdit);
	}
	
	@Override
	public Character getCharacter(long id) {
		return (Character) sessionFactory.getCurrentSession().getNamedQuery("Character.one").setLong("id", id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Character> getAllCharacters() {
		return sessionFactory.getCurrentSession().getNamedQuery("Character.all").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Character> getCharacterByType(String type) {
		return sessionFactory.getCurrentSession().getNamedQuery("Character.byType").setString("type", type).list();
	}
	
}