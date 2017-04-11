package edu.unq.desapp.groupA.backend.service;

import edu.unq.desapp.groupA.backend.model.Usuario;
import edu.unq.desapp.groupA.backend.repository.UserRepository;

public class UserService {
	
	private UserRepository repository;

	public UserRepository getRepository() {
		return repository;
	}

	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

	public UserService(UserRepository userRepository) {
		this.repository = userRepository;
	}

	public Usuario createUser(String username, String password, String email){
		Usuario user = new Usuario();
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);
		this.repository.save(user);
		return user;
	}
	
	
}
