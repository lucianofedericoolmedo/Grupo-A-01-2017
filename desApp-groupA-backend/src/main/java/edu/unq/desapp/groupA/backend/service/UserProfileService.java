package edu.unq.desapp.groupA.backend.service;

import edu.unq.desapp.groupA.backend.model.Thresold;
import edu.unq.desapp.groupA.backend.model.UserProfile;
import edu.unq.desapp.groupA.backend.model.Usuario;
import edu.unq.desapp.groupA.backend.repository.UserProfileRepository;

public class UserProfileService {
	
	private UserProfileRepository repository;

	public UserProfileService(UserProfileRepository repository) {
		this.repository = repository;
	}

	public UserProfileRepository getRepository() {
		return repository;
	}

	public void setRepository(UserProfileRepository repository) {
		this.repository = repository;
	}

	public UserProfile createUserProfile(Usuario user, Thresold thresold) {
		UserProfile userProfile = new UserProfile();
		userProfile.setThresold(thresold);
		userProfile.setUser(user);
		this.repository.save(userProfile);
		return userProfile;
	}

	

}
