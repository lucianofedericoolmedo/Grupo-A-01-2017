package edu.unq.desapp.groupA.backend.webservice;

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

import edu.unq.desapp.groupA.backend.dto.ProductCrud;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.repository.pagination.PageResponse;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.service.ProductService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/products")
public class ProductRest extends GenericRest<Product> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	private ProductService productService;

	@Override
	public GenericService<Product> getService() {
		return productService;
	}

	@GET
	@Path("/all")
	public Response findAll(@Context HttpServletRequest request) {
		return super.findAll();
	}

	@GET
	@Path("/{id}")
	public Response find(@Context HttpServletRequest request, @PathParam("id") final Long id) {
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
	@Path("/find-not-in-shopping-list")
	public Response findByPageNotInShoppingList(@Context HttpServletRequest request,
			@QueryParam("pageNumber") Integer pageNumber,
			@QueryParam("pageSize") Integer pageSize,
			@QueryParam("shoppingListId") Integer shoppingListId) {
		try {
			PageResponse<Product> productsPage = productService.findByPageProductsNotInShoppingList(pageNumber, pageSize, shoppingListId);
			return responseGenerator.buildSuccessResponse(productsPage);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}

	@GET
	public Response ok() {
		return responseGenerator.responseOK("OK");
	}
	
	@POST
	public Response create(@Context HttpServletRequest request,
			Product product) {
		return super.create(product);
	}
	
	@POST
	@Path("create-dto")
	public Response createFromDto(@Context HttpServletRequest request,
			ProductCrud productCrud) {
		try {
			productService.createFromDto(productCrud);
			return responseGenerator.buildResponse(Status.OK);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}

	@PUT
	@Path("/{id}")
	public Response update(@Context HttpServletRequest request,
			Product product) {
		return super.update(product);
	}
	
	@PUT
	@Path("update-dto/{id}")
	public Response updateFromDto(@Context HttpServletRequest request,
			ProductCrud productCrud) {
		try {
			productService.updateFromDto(productCrud);
			return responseGenerator.buildResponse(Status.OK);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@Context HttpServletRequest request,
			@PathParam("id") final Long id) {
		return super.delete(id);
	}

}
