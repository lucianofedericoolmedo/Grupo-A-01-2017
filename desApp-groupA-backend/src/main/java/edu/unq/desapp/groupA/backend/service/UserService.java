package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.Usuario;

public class UserService {

	List<Usuario> users;

	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
	}
	
	public UserService(){
		this.users = new ArrayList<Usuario>();
	}
	
	public Usuario createUser(){
		Usuario usuario = new Usuario();
		return usuario;
	}
	
	
}
