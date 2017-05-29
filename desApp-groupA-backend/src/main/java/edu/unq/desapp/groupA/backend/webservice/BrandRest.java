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

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.service.BrandService;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;


@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/brands")
public class BrandRest extends GenericRest<Brand> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	public BrandService brandService;

	@Override
	public GenericService<Brand> getService() {
		return brandService;
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
	public Response create(@Context HttpServletRequest request,Brand brand) {
		return super.create(brand);
	}

	@PUT
	public Response update(@Context HttpServletRequest request,
			@PathParam("id") final Long id, Brand brand) {
		Brand originalBrand = this.brandService.find(id);
		originalBrand.setName(brand.getName());
		return super.update(originalBrand);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") final Long id) {
		return super.delete(id);
	}

}
