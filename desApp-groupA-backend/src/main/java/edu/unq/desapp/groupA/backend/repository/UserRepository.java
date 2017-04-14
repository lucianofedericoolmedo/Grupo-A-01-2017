package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.Usuario;

public class UserRepository {

	List<Usuario> users;

	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
	}
	
	public UserRepository(){
		this.users = new ArrayList<Usuario>();
	}

	public void save(Usuario user) {
		this.users.add(user);
	}
}
