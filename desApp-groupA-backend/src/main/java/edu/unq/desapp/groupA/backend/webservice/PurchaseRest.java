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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.dto.PurchaseDTO;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.repository.pagination.PageResponse;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.service.PurchaseService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/purchases")
public class PurchaseRest extends GenericRest<Purchase> {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	private PurchaseService purchaseService;

	@Override
	public GenericService<Purchase> getService() {
		return purchaseService;
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
	@Path("/get-dto/{purchaseId}")
	public Response findPurchaseDto(@Context HttpServletRequest request, 
			@PathParam("purchaseId") final Long id) {
		try {
			return responseGenerator.buildSuccessResponse(purchaseService.findDtoById(id));
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@GET
	@Path("/find-by-page-for-user")
	public Response findPageByUserId(@Context HttpServletRequest request,
			@QueryParam("userId") Long userId,
			@QueryParam("pageNumber") Integer pageNumber,
			@QueryParam("pageSize") Integer pageSize) {
		try {
			PageResponse<PurchaseDTO> usersPurchasePage = purchaseService.findPageByUserId(pageNumber, pageSize, userId);
			return responseGenerator.buildSuccessResponse(usersPurchasePage);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@GET
	public Response ok() {
		return responseGenerator.responseOK("OK");
	}
	
	@POST
	public Response create(Purchase purchase) {
		return super.create(purchase);
	}

	@PUT
	public Response update(Purchase purchase) {
		return super.update(purchase);
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") final Long id) {
		return super.delete(id);
	}

}
