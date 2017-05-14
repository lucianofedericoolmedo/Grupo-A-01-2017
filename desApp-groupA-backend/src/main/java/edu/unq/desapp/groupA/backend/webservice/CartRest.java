package edu.unq.desapp.groupA.backend.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.service.CartService;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/cart")
public class CartRest extends GenericRest<Cart> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public GenericService<Cart> getService() {
		return cartService;
	}
	
	@GET
	@Path("/all")
	public Response findAll() {
		return super.findAll();
	}

	@GET
	@Path("/{id}")
	public Response find(@PathParam("id") final Long id) {
		return super.find(id);
	}
	
	@GET
	public Response ok() {
		return responseGenerator.responseOK("OK");
	}

	@POST
	public Response create(Cart cart) {
		return super.create(cart);
	}

	@POST
	@Path("for-user/{userId}")
	public Response createForUser(@PathParam("userId") Long userId, Cart cart) {
		try {
			Cart savedCart = cartService.create(userId, cart);
			return responseGenerator.buildSuccessResponse(savedCart);
		} catch (Exception e) {
			return responseGenerator.responseBadRequest(e.getMessage());
		}
	}

	@PUT
	public Response update(Cart cart) {
		return super.update(cart);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") final Long id) {
		return super.delete(id);
	}

}
