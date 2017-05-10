package edu.unq.desapp.groupA.backend.webservice;


import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.service.BrandService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;

@Service
@Path("/brand")
public class BrandRest extends ResponseGenerator{

	@Autowired
	public BrandService brandService;
	
	@GET
	@Path("/brands")
	@Produces("application/json")
	public List<Brand> events() {
		return this.brandService.findAll();
	}
	
	@GET
	@Produces("application/json")
	public Response ok() {
		return responseOK("OK");
	}

	
	
	@DELETE
	@Path("/delete/{id}")
	@Produces("application/json")
	public Response deleteProfile(@PathParam("id") final Long id) {

		try {
			Brand brand = this.brandService.find(id);
			this.brandService.delete(brand);
		} catch (Exception e) {
			return responseBadRequest("{Error: Can't delete Event or invalid ID ,"
					+ "Status: FAIL}");
		}
		return responseOK("{Action:"+"Event Deleted"+","
						  +"ID:"+id+","
						  +"Status"+": "+"OK"+"}");
	}
	
	
	

}


