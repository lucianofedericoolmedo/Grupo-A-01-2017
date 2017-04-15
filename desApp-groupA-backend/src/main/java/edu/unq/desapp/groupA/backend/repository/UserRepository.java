package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.User;

public class UserRepository {

	List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public UserRepository(){
		this.users = new ArrayList<User>();
	}

	public void save(User user) {
		this.users.add(user);
	}
}
