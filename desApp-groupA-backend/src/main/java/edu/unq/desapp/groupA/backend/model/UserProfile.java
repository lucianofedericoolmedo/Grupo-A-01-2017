package edu.unq.desapp.groupA.backend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users_profiles")
public class UserProfile extends PersistenceEntity {

	private static final long serialVersionUID = -5227774846544481307L;

	// Instance Variables
	@OneToOne
	private User user;

	@OneToOne
	private UserData userData;

	@OneToMany
	private List<Threshold> thresolds;
	
	// Getters and Setters
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public List<Threshold> getThresolds() {
		return thresolds;
	}

	public void setThresolds(List<Threshold> thresolds) {
		this.thresolds = thresolds;
	}

}
