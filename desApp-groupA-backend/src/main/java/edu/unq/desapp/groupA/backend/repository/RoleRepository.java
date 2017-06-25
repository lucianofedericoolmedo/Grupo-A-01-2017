package edu.unq.desapp.groupA.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.Role;

@Repository
@SuppressWarnings("unchecked")
public class RoleRepository extends HibernateGenericDAO<Role> implements GenericRepository<Role> {

	private static final long serialVersionUID = -5758182420532911963L;

	@Transactional
	public Role findByName(String roleName) {
		String query = "SELECT role FROM " + this.persistentClass.getName() + " role "
						+ "WHERE role.name = ? ";
		List<Role> roles = (List<Role>) this.getHibernateTemplate().find(query, roleName);
		return roles.isEmpty() ? null : roles.get(0);
	}

	@Override
	public Class<Role> getDomainClass() {
		return Role.class;
	}

}
