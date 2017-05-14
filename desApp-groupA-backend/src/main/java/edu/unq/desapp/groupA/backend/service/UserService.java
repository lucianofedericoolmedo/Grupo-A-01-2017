package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.User;
import edu.unq.desapp.groupA.backend.repository.UserRepository;


@Service
@Transactional
public class UserService extends GenericService<User> {
	
	@Autowired
	private UserRepository repository;

	public UserRepository getRepository() {
		return repository;
	}

	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

	public UserService() { }

	public User createUser(String username, String password, String email){
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);
		this.repository.save(user);
		return user;
	}
	
}
