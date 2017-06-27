package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="users_profiles")
public class UserProfile extends PersistenceEntity {

	private static final long serialVersionUID = -5227774846544481307L;

	// Instance Variables
	@JsonIgnore
	@OneToOne(cascade=CascadeType.MERGE)
	private UserCredential user;

	@OneToOne(cascade=CascadeType.ALL)
	private UserData userData;

	@OneToMany(mappedBy="userProfile")
	private List<Threshold> thresolds;

	// Constructors
	public UserProfile() {
		this.thresolds = new LinkedList<Threshold>();
	}
	
	public UserProfile(UserCredential userCredential, UserData userData) {
		this.user = userCredential;
		this.userData = userData;
		this.thresolds = new LinkedList<Threshold>();
	}

	// Getters and Setters
	public UserCredential getUser() {
		return user;
	}
	
	public void setUser(UserCredential user) {
		this.user = user;
	}

	public List<Threshold> getThresolds() {
		return thresolds;
	}

	public void setThresolds(List<Threshold> thresolds) {
		this.thresolds = thresolds;
	}

}
