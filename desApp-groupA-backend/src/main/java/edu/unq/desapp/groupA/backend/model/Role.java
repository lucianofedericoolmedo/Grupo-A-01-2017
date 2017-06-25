package edu.unq.desapp.groupA.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role extends PersistenceEntity {

	private static final long serialVersionUID = -5112884246415427945L;

	// INstance Variables
	private String name;

	// Constructors
	public Role() {
		
	}
	
	public Role(String name) {
		this.name = name; 
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
