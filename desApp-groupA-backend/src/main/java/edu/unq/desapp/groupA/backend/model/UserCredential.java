package edu.unq.desapp.groupA.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserCredential extends PersistenceEntity {

	private static final long serialVersionUID = -5002653969322746767L;

	// Instance Variables
	private String username;	
	private String password;
	private String email;

	// Constructors
	public UserCredential() {
		
	}
	
	public UserCredential(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// Getters and Setters
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isSameUser(UserCredential otherUser){
		return this.username.equals(otherUser.getUsername());
	}

}
