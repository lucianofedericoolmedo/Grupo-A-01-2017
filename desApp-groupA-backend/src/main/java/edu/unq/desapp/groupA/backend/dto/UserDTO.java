package edu.unq.desapp.groupA.backend.dto;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import edu.unq.desapp.groupA.backend.model.Role;
import edu.unq.desapp.groupA.backend.model.UserCredential;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDTO {

	// Instance Variables
	private Long userId;
	
	private Set<Role> roles;

	// Constructors
	public UserDTO() {
		
	}
	
	public UserDTO(UserCredential userCredential) {
		this.userId = userCredential.getId();
		this.roles = new HashSet<Role>(userCredential.getRoles());
	}
	
	// Getters and Setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
