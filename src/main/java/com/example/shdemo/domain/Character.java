package com.example.shdemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Character.all", query = "Select c from Character c"),
	@NamedQuery(name = "Character.one", query = "Select c from Character c where c.id = :id"),
	@NamedQuery(name = "Character.byType", query = "Select c from Character c where c.type = :type")
})
public class Character {
	
	private long id;
	private String name;
	private String quality;
	private String type;
	
	public Character(){
		
	}
	
	public Character(String name, String quality, String type){
		super();
		this.name = name;
		this.quality = quality;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}