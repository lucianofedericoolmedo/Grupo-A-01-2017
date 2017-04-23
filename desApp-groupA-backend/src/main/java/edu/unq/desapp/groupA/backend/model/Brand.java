package edu.unq.desapp.groupA.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand extends PersistenceEntity {

	private static final long serialVersionUID = 1276171792953917236L;
	// Instance Variables
	private String name;

	// Constructors
	public Brand() {
		
	}

	public Brand(String name) {
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
