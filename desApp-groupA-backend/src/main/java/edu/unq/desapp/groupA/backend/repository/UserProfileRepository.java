package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.UserProfile;

public class UserProfileRepository {

	private List<UserProfile> userProfiles;

	public List<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public UserProfileRepository(){
		this.setUserProfiles(new ArrayList<UserProfile>());
	}

	public void save(UserProfile userProfile) {
		this.userProfiles.add(userProfile);
	}
	
}
