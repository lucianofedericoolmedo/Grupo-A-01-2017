package edu.unq.desapp.groupA.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.utils.JSONDateDeserialize;
import edu.unq.desapp.groupA.backend.utils.JSONDateSerialize;

@MappedSuperclass
public class PersistenceEntity implements Serializable {

	private static final long serialVersionUID = 4056818895685613967L;

	// Instance Variables
	@Id
    @Column(unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;

	@JsonDeserialize(using = JSONDateDeserialize.class)
    @JsonSerialize(using = JSONDateSerialize.class)    
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
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
