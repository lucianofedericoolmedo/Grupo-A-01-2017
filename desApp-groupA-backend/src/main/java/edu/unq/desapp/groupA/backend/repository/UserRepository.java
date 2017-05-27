package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.User;


@Repository
public class UserRepository extends HibernateGenericDAO<User> implements GenericRepository<User>{

	private static final long serialVersionUID = -2433619667876428408L;

	List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public UserRepository(){
		this.users = new ArrayList<User>();
	}

	@Override
	public Class<User> getDomainClass() {
		return User.class;
	}
}
