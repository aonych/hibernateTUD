package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "Movie.all", query = "Select m from Movie m"),
	@NamedQuery(name = "Movie.one", query = "Select m from Movie m where m.id = :id"),
	@NamedQuery(name = "Movie.byCountry", query = "Select m from Movie m where m.country = :country")
})
public class Movie {
	
	private long id;
	private String title;
	private String country;
	private String production;
	private int year;
	
	private List<Character> characters = new ArrayList<Character>();
	
	public Movie(){
		
	}
	
	public Movie(String title, String country, String production, int year){
		super();
		this.title = title;
		this.country = country;
		this.production = production;
		this.year = year;
	}
	
	public Movie(String title, String country, String production, int year, List<Character> characters){
		super();
		this.title = title;
		this.country = country;
		this.production = production;
		this.year = year;
		this.characters = characters;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}
	
}