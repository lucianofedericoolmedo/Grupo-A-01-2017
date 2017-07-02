package edu.unq.desapp.groupA.backend.webservice;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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

import edu.unq.desapp.groupA.backend.dto.DiscountDTO;
import edu.unq.desapp.groupA.backend.model.Discount;
import edu.unq.desapp.groupA.backend.model.Priority;
import edu.unq.desapp.groupA.backend.service.DiscountService;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;


@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/discounts")
public class DiscountRest extends GenericRest<Discount> {

	@Autowired
	private ResponseGenerator responseGenerator;

	@Autowired
	public DiscountService discountsService;

	@Override
	public GenericService<Discount> getService() {
		return discountsService;
	}

	@GET
	@Path("/all-possibles")
	public Response fetchAllThresholdsPossibles(@Context HttpServletRequest request) {
		try {
			return responseGenerator.buildSuccessResponse(discountsService.fetchAllDiscountsPossibles());
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@GET
	@Path("/{id}")
	public Response find(@Context HttpServletRequest request, @PathParam("id") Long discountId) {
		return super.find(discountId);
	}
	
	@GET
	@Path("/find-by-page")
	public Response findByPage(@Context HttpServletRequest request,
			@QueryParam("pageNumber") Integer pageNumber,
			@QueryParam("pageSize") Integer pageSize) {
		return super.findByPage(pageNumber, pageSize);
	}
	
	@GET
	@Path("/fetch-priorities")
	public Response findPriorities(@Context HttpServletRequest request) {
		try {
			return responseGenerator.buildResponse(Priority.values(), Status.OK);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@GET
	@Path("/testing-discount")
	public Response findDiscount(@Context HttpServletRequest request) {
		try {
			return responseGenerator.buildResponse(discountsService.findActiveDiscountsByPriority(Priority.LOW), Status.OK);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}

	@POST
	@Path("/per-product")
	public Response createPerProduct(@Context HttpServletRequest request, DiscountDTO discount) {
		try {
			discountsService.createPerProduct(discount);
			return responseGenerator.responseOK("Created");
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@POST
	@Path("/per-product-category")
	public Response createPerProductCategory(@Context HttpServletRequest request, DiscountDTO discount) {
		try {
			discountsService.createPerProductCategory(discount);
			return responseGenerator.responseOK("Created");
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@POST
	@Path("/per-product-quantity")
	public Response createPerProductQuantity(@Context HttpServletRequest request, DiscountDTO discount) {
		try {
			discountsService.createPerProductQuantity(discount);
			return responseGenerator.responseOK("Created");
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}

	@PUT
	@Path("/{id}")
	public Response update(@Context HttpServletRequest request, Discount discount) {
		return super.update(discount);
	}

	@GET
	public Response ok() {
		return responseGenerator.responseOK("OK");
	}
	
}
