package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.UserCredential;


@SuppressWarnings("unchecked")
@Repository
public class UserRepository extends HibernateGenericDAO<UserCredential> implements GenericRepository<UserCredential>{

	private static final long serialVersionUID = -2433619667876428408L;

	List<UserCredential> users;

	public List<UserCredential> getUsers() {
		return users;
	}

	public void setUsers(List<UserCredential> users) {
		this.users = users;
	}
	
	public UserRepository(){
		this.users = new ArrayList<UserCredential>();
	}

	@Override
	public Class<UserCredential> getDomainClass() {
		return UserCredential.class;
	}

	public UserCredential findByUsername(String username) {
		String query = "SELECT user FROM " + persistentClass.getName() + " user "
						+ "WHERE user.username = ? ";
		List<UserCredential> users = (List<UserCredential>) this.getHibernateTemplate().find(query, username);
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}
}
