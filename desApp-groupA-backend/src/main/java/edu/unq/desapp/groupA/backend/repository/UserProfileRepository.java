package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.UserProfile;


@Repository
public class UserProfileRepository extends HibernateGenericDAO<UserProfile> implements GenericRepository<UserProfile>{

	private static final long serialVersionUID = 6537732777373591739L;

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

	@Override
	protected Class<UserProfile> getDomainClass() {
		return UserProfile.class;
	}
	
}
