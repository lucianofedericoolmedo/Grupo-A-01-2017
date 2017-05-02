package edu.unq.desapp.groupA.backend.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;

@MappedSuperclass
public class PersistenceEntity implements Serializable {

	private static final long serialVersionUID = 4056818895685613967L;

	// Instance Variables
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
