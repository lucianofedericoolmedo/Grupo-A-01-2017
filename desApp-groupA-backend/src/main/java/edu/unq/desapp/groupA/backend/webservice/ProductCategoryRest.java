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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.service.ProductCategoryService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/products-categories")
public class ProductCategoryRest extends GenericRest<ProductCategory> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	private ProductCategoryService productCategoryService;

	@Override
	public GenericService<ProductCategory> getService() {
		return productCategoryService;
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
	public Response create(@Context HttpServletRequest request,ProductCategory productCategory) {
		return super.create(productCategory);
	}

	@PUT
	public Response update(@Context HttpServletRequest request,ProductCategory productCategory) {
		return super.update(productCategory);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@Context HttpServletRequest request,@PathParam("id") final Long id) {
		return super.delete(id);
	}

}
