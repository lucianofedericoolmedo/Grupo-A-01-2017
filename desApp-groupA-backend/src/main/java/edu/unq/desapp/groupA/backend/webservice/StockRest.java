package edu.unq.desapp.groupA.backend.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.Stock;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.service.StockService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;


@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/stock")
public class StockRest  extends GenericRest<Stock> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	public StockService stockService;

	@Override
	public GenericService<Stock> getService() {
		return stockService;
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
	@Path("/for-product/{productId}")
	public Response findLastForProduct(@PathParam("productId") final Long productId) {
//		try {
			return responseGenerator.buildSuccessResponse(stockService.findForProductId(productId));
			/*
		} catch (Exception e) {
			return responseGenerator.buildResponse(Status.BAD_REQUEST);
		}
		*/
	}
	
	@GET
	public Response ok() {
		return responseGenerator.responseOK("OK");
	}
	
	@GET
	@Path("/find-by-page")
	public Response findByPage(@QueryParam("pageNumber") Integer pageNumber,
			@QueryParam("pageSize") Integer pageSize) {
		return super.findByPage(pageNumber, pageSize);
	}

}
