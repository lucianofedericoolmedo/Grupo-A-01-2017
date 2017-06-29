package edu.unq.desapp.groupA.backend.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.UserProfile;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.service.UserProfileService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/users-profiles")
public class UserProfileRest  extends GenericRest<UserProfile> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	private UserProfileService userProfileService;

	@Override
	public GenericService<UserProfile> getService() {
		return userProfileService;
	}

	@GET
	@Path("/all")
	public Response findAll() {
		return super.findAll();
	}

	@GET
	@Path("/{id}")
	public Response find(@Context HttpServletRequest request, @PathParam("id") Long id) {
		return super.find(id);
	}
	
	@GET
	@Path("/by-user/{userId}")
	public Response findByUserId(@Context HttpServletRequest request, @PathParam("userId") Long id) {
		try {
			return responseGenerator.buildSuccessResponse(userProfileService.findByUserId(id));
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@GET
	public Response ok() {
		return responseGenerator.responseOK("OK");
	}

	@PUT
	@Path("/{idca}")
	public Response update(@Context HttpServletRequest request, UserProfile userProfile) {
		return super.update(userProfile);
	}

}
