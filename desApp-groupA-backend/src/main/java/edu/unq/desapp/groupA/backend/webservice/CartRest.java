package edu.unq.desapp.groupA.backend.webservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.PaymentTurn;
import edu.unq.desapp.groupA.backend.service.CartService;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;
import edu.unq.desapp.groupA.backend.utils.WrappedValue;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/carts")
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
	public Response findAll(@Context HttpServletRequest request) {
		return super.findAll();
	}

	@GET
	@Path("/{id}")
	public Response find(@Context HttpServletRequest request, 
			@PathParam("id") final Long id) {
		return super.find(id);
	}

	@GET
	@Path("/find-by-page")
	public Response findByPage(@Context HttpServletRequest request, 
			@QueryParam("pageNumber") Integer pageNumber,
			@QueryParam("pageSize") Integer pageSize) {
		return super.findByPage(pageNumber, pageSize);
	}
	
	@GET
	public Response ok() {
		return responseGenerator.responseOK("OK");
	}
	
	@GET
	@Path("/request-turn/{id}")
	public Response requestTurn(@Context HttpServletRequest request, @PathParam("id") Long cartId) {
		try {
			PaymentTurn turn = cartService.requestTurnToPay(cartId);
			return responseGenerator.buildSuccessResponse(turn);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@POST
	@Path("/confirm-turn")
	public Response requestTurn(@Context HttpServletRequest request, PaymentTurn turn) {
		try {
			cartService.queueForPurchase(turn);
			return responseGenerator.buildSuccessResponse(Status.OK);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}

	@POST
	public Response create(@Context HttpServletRequest request, Cart cart) {
		return super.create(cart);
	}

	@POST
	@Path("for-user/{userId}")
	public Response createForUser(@Context HttpServletRequest request, 
			@PathParam("userId") Long userId, Cart cart) {
		try {
			Cart savedCart = cartService.create(userId, cart);
			return responseGenerator.buildSuccessResponse(savedCart);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}

	@POST
	@Path("create-from-shopping-list-for-user")
	public Response createFromShoppingListForUser(@Context HttpServletRequest request, 
			@RequestParam Map<String, WrappedValue<Long>> params) {
		try {
			Long shoppingListId = params.get("shoppingListId").getValue();
			Long userId = params.get("userId").getValue();
			Cart savedCart = cartService.create(shoppingListId, userId);
			return responseGenerator.buildSuccessResponse(savedCart);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}

	@PUT
	@Path("{id}")
	public Response update(@Context HttpServletRequest request, Cart cart) {
		return super.update(cart);
	}
	
	@PUT
	@Path("check-item-cart/{id}")
	public Response setValueToItemCart(@Context HttpServletRequest request, @PathParam("id") Long itemCartId, 
			Map<String, Object> data) {
		try {
			Boolean checked = (Boolean) data.get("checked");
			Integer newQuantity = null;
			if (checked) {
				newQuantity = (Integer) data.get("newQuantity");
			}
			cartService.setValueToItem(itemCartId, checked, newQuantity);
			return responseGenerator.buildResponse(Status.OK);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@Context HttpServletRequest request, @PathParam("id") final Long id) {
		return super.delete(id);
	}

}
