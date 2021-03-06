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

import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.service.ItemCartService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;


@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/items-cart")
public class ItemCartRest extends GenericRest<ItemCart> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	private ItemCartService itemCartService;

	@Override
	public GenericService<ItemCart> getService() {
		return itemCartService;
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
	public Response create(ItemCart itemCart) {
		return super.create(itemCart);
	}

	@POST
	@Path("for-cart/{cartId}")
	public Response createForCart(@PathParam("cartId") Long cartId, ItemCart itemCart) {
		try {
			ItemCart savedItemCart = itemCartService.createForCartId(cartId, itemCart); 
			return responseGenerator.buildSuccessResponse(savedItemCart);
		} catch (Exception e) {
			return responseGenerator.responseBadRequest(e.getMessage());
		}
	}

	@PUT
	public Response update(ItemCart itemCart) {
		return super.update(itemCart);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") final Long id) {
		return super.delete(id);
	}

}
