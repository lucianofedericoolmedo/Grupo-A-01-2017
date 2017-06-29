package edu.unq.desapp.groupA.backend.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import edu.unq.desapp.groupA.backend.utils.StringValidator;

@Entity
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserCredential extends PersistenceEntity {

	private static final long serialVersionUID = -5002653969322746767L;

	// Instance Variables
	private String username;	
	private String password;
	private String email;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name="users_roles", 
		joinColumns={@JoinColumn(name="user_id")}, 
		inverseJoinColumns={@JoinColumn(name="role_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Role> roles;

	// Constructors
	public UserCredential() {
		this.roles = new LinkedList<Role>();
	}
	
	public UserCredential(String username, String password) {
		this.username = username;
		this.password = password;
		this.roles = new LinkedList<Role>();
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	// Logic
	/**
	 * Adds the given Role to the Entity if not already added previously.
	 * @param roleToAdd : The Role to add.
	 */
	public void addRole(Role roleToAdd) {
		for (Role role : roles) {
			if (role.getId().equals(roleToAdd.getId())) {
				return;
			}
		}
		this.roles.add(roleToAdd);
	}
	
	public void addRoles(Collection<Role> roles) {
		for (Role role : roles) {
			this.addRole(role);
		}
	}
	
	public Boolean isWellFormed() {
		return StringValidator.isValidString(username) && StringValidator.isValidString(password);
	}

}
