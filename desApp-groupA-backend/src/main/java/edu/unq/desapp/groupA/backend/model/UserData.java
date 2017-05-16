package edu.unq.desapp.groupA.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users_data")
public class UserData extends PersistenceEntity {

	private static final long serialVersionUID = 6374630332450175924L;

	// Instance Variables
	private String name;
	
	private String surname;
	
	private Date birthday;
	
	private String dni;

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
}
