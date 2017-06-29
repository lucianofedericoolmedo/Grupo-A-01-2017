package edu.unq.desapp.groupA.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.UserProfile;


@Repository
@SuppressWarnings("unchecked")
public class UserProfileRepository extends HibernateGenericDAO<UserProfile> implements GenericRepository<UserProfile>{

	private static final long serialVersionUID = 6537732777373591739L;

	@Override
	public Class<UserProfile> getDomainClass() {
		return UserProfile.class;
	}

	public UserProfile findByUserId(Long id) {
		String query = "FROM " + persistentClass.getName() + " profile "
						+ "WHERE profile.user.id = ? ";
		List<UserProfile> profiles = (List<UserProfile>) this.getHibernateTemplate().find(query, id);
		if (profiles.isEmpty()) {
			return null;
		} else {
			return profiles.get(0);
		}
	}
	
}
