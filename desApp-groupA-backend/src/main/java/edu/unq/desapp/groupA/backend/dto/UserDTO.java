package edu.unq.desapp.groupA.backend.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import edu.unq.desapp.groupA.backend.model.UserCredential;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDTO {

	// Instance Variables
	private Long userId;

	// Constructors
	public UserDTO() {
		
	}
	
	public UserDTO(UserCredential userCredential) {
		this.userId = userCredential.getId();
	}
	
	// Getters and Setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
