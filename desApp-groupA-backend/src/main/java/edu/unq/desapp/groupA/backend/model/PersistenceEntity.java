package edu.unq.desapp.groupA.backend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import edu.unq.desapp.groupA.backend.utils.JSONDateDeserialize;
import edu.unq.desapp.groupA.backend.utils.JSONDateSerialize;

@MappedSuperclass
public class PersistenceEntity implements Serializable {

	private static final long serialVersionUID = 4056818895685613967L;

	// Instance Variables
	@Id
    @Column(unique = true)
	@GeneratedValue(strategy=GenerationType.TABLE)
	protected Long id;

	@JsonDeserialize(using = JSONDateDeserialize.class)
    @JsonSerialize(using = JSONDateSerialize.class)    
	protected Date creationDate;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@PrePersist
	public void onCreate() {
		setCreationDate(new Date());
	}

}
