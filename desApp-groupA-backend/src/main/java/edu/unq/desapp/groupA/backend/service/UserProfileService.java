package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.Thresold;
import edu.unq.desapp.groupA.backend.model.UserProfile;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class UserProfileService {
	
	private List<UserProfile> userProfiles;

	public UserProfileService(){
		this.setUserProfiles(new ArrayList<UserProfile>());
	}

	public UserProfile createUserProfile(Usuario user, Thresold thresold) {
		UserProfile userProfile = new UserProfile();
		userProfile.setThresold(thresold);
		userProfile.setUser(user);
		this.userProfiles.add(userProfile);
		return userProfile;
	}

	public List<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

}
