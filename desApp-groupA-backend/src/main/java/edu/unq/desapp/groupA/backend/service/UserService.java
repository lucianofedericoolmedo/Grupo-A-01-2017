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
	
	public Usuario createUser(String username, String password, String email){
		Usuario user = new Usuario();
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);
		this.users.add(user);
		return user;
	}
	
	
}
