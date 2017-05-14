package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.Threshold;
import edu.unq.desapp.groupA.backend.model.UserProfile;
import edu.unq.desapp.groupA.backend.model.User;
import edu.unq.desapp.groupA.backend.repository.UserProfileRepository;


@Service
public class UserProfileService extends GenericService<UserProfile> {
	
	@Autowired
	private UserProfileRepository repository;

	public UserProfileService() { }

	public UserProfileRepository getRepository() {
		return repository;
	}

	public void setRepository(UserProfileRepository repository) {
		this.repository = repository;
	}

	public UserProfile createUserProfile(User user, Threshold thresold) {
		UserProfile userProfile = new UserProfile();
//		userProfile.setThresold(thresold);
		userProfile.setUser(user);
		this.repository.save(userProfile);
		return userProfile;
	}

}
