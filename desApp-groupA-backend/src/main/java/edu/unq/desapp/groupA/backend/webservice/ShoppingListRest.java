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
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.service.ItemShoppingListService;
import edu.unq.desapp.groupA.backend.service.ShoppingListService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/shopping-lists")
public class ShoppingListRest extends GenericRest<ShoppingList> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	private ShoppingListService shoppingListService;
	
	@Autowired
	private ItemShoppingListService itemShoppingListService;

	@Override
	public GenericService<ShoppingList> getService() {
		return shoppingListService;
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
	public Response create(ShoppingList shoppingList) {
		return super.create(shoppingList);
	}

	@POST
	@Path("create-item-for/{shoppingListId}")
	public Response create(@PathParam("shoppingListId") Long shoppingListId, ItemShoppingList itemShoppingList) {
		try {
			ItemShoppingList createdItem = shoppingListService.createItemForShoppingList(shoppingListId, itemShoppingList);
			return responseGenerator.buildSuccessResponse(createdItem);
		} catch (Exception e) {
			return responseGenerator.responseBadRequest(e.getMessage());
		}
	}
	
	@PUT
	@Path("update-item/{id}")
	public Response create(ItemShoppingList itemShoppingList) {
		try {
			itemShoppingListService.update(itemShoppingList);
			return responseGenerator.buildResponse(Status.OK);
		} catch (Exception e) {
			return responseGenerator.responseBadRequest(e.getMessage());
		}
	}

	@PUT
	@Path("{id}")
	public Response update(ShoppingList shoppingList) {
		return super.update(shoppingList);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") final Long id) {
		return super.delete(id);
	}
	
	@DELETE
	@Path("/remove-item-in/{itemId}/{shoppingListId}")
	public Response deleteItemFromShoppingList(@PathParam("itemId") Long itemId,
			@PathParam("shoppingListId") Long shoppingListId) {
		try {
			shoppingListService.removeItemFromShoppingList(itemId, shoppingListId);
			return responseGenerator.buildResponse(Status.OK);
		} catch (Exception e) {
			return responseGenerator.responseBadRequest(e.getMessage());
		}
	}

}
