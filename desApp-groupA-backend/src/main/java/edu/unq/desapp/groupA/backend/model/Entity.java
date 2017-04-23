package edu.unq.desapp.groupA.backend.model;

import java.io.Serializable;
import org.joda.time.DateTime;

public class Entity implements Serializable {

	private static final long serialVersionUID = 4056818895685613967L;

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
