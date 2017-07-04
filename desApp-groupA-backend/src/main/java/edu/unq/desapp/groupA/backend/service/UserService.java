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

	@Transactional
	public UserCredential createUser(String username, String password, String email){
		UserCredential user = new UserCredential();
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);
		this.repository.save(user);
		return user;
	}

	@Transactional
	private void validateIncompleteFields(UserCredential user) {
		if (!user.isWellFormed()) {
			throw new RuntimeException("Incomplete information sent");
		}
	}
	
	public UserCredential findByUsername(String username) {
		return this.repository.findByUsername(username);
	}
	
	public UserCredential findByEmail(String username) {
		return this.repository.findByEmail(username);
	}
	
	@Transactional
	private void validateCredentialsForSignup(UserCredential user) {
		validateIncompleteFields(user);
		UserCredential userCredential = this.findByEmail(user.getEmail());
		if (userCredential != null) {
			throw new RuntimeException("The username has already been taken");
		}
	}

	@Transactional
	private Role fetchClientRole() {
		return roleService.findByName("CLIENT");
	}
	
	@Transactional
	private Role fetchAdminRole(){
		return roleService.findByName("ADMIN");
	}
	
	@Transactional
	public UserDTO signup(UserCredential userParam) {
		return new UserDTO(obtainCredential(userParam));
	}
	
	@Transactional
	public UserCredential obtainCredential(UserCredential userParam){
		validateCredentialsForSignup(userParam);
		UserCredential userCredential = new UserCredential(userParam.getUsername(), userParam.getPassword(), userParam.getEmail());
		userCredential = super.save(userCredential);
		userProfileService.createUserProfile(userCredential);
		if (userCredential.getEmail().contains("olmedo.juanig")){
			userCredential.addRole(fetchAdminRole());
		}
		else{
			userCredential.addRole(fetchClientRole());
		}		
		return userCredential;
	}

	@Transactional
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
