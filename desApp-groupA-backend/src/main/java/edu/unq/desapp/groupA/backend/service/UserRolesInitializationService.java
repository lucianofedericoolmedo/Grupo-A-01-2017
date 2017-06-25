package edu.unq.desapp.groupA.backend.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.Role;
import edu.unq.desapp.groupA.backend.model.UserCredential;

@Service
public class UserRolesInitializationService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@PostConstruct
	private void initialize() {
		List<Role> roles = new LinkedList<Role>();
		roles.add(roleService.save(new Role("ADMIN")));
		roles.add(roleService.save(new Role("CLIENT")));
		roles.add(roleService.save(new Role("CASHIER")));
		
		UserCredential adminUser = new UserCredential("admin", "admin");
		adminUser.addRoles(roles);
		userService.save(adminUser);
	}
	
}
