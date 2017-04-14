package edu.unq.desapp.groupA.backend.model;

import org.joda.time.DateTime;

public class Entity {

	// Instance Variables
	protected Long id;

	protected DateTime creationDate;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

}
