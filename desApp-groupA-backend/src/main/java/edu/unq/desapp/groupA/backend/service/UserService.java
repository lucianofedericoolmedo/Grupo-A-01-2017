package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.dto.UserDTO;
import edu.unq.desapp.groupA.backend.model.Role;
import edu.unq.desapp.groupA.backend.model.UserCredential;
import edu.unq.desapp.groupA.backend.repository.UserRepository;


@Service
@Transactional
public class UserService extends GenericService<UserCredential> {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private RoleService roleService;

	public UserRepository getRepository() {
		return repository;
	}

	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

	public UserService() { }

	public UserCredential createUser(String username, String password, String email){
		UserCredential user = new UserCredential();
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);
		this.repository.save(user);
		return user;
	}

	private void validateIncompleteFields(UserCredential user) {
		if (!user.isWellFormed()) {
			throw new RuntimeException("Incomplete information sent");
		}
	}
	
	public UserCredential findByUsername(String username) {
		return this.repository.findByUsername(username);
	}
	
	private void validateCredentialsForSignup(UserCredential user) {
		validateIncompleteFields(user);
		UserCredential userCredential = this.findByUsername(user.getUsername());
		if (userCredential != null) {
			throw new RuntimeException("The username has already been taken");
		}
	}

	private Role fetchClientRole() {
		return roleService.findByName("CLIENT");
	}
	
	public UserDTO signup(UserCredential userParam) {
		validateCredentialsForSignup(userParam);
		UserCredential userCredential = new UserCredential(userParam.getUsername(), userParam.getPassword());
		userCredential = super.save(userCredential);
		userProfileService.createUserProfile(userCredential);
		userCredential.addRole(fetchClientRole());
		return new UserDTO(userCredential);
	}

	public UserDTO signin(UserCredential user) {
		validateIncompleteFields(user);
		UserCredential fetchedUser = this.findByUsername(user.getUsername());
		if (fetchedUser != null && fetchedUser.getPassword().equals(user.getPassword())) {
			return new UserDTO(fetchedUser);
		} else {
			throw new RuntimeException("Incorrect username or password");
		}
	}

}
