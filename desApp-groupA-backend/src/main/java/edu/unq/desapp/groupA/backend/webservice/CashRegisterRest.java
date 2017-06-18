package edu.unq.desapp.groupA.backend.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.service.CashRegisterManagement;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;
import edu.unq.desapp.groupA.backend.utils.WrappedValue;

@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/cash-registers")
public class CashRegisterRest {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	private CashRegisterManagement cashRegisterManagement;

	@GET
	public Response ok() {
		return responseGenerator.responseOK("OK");
	}
	
	@POST
	@Path("activate-cash-register")
	public Response activateCashRegister(@Context HttpServletRequest request, WrappedValue<Long> cashRegisterCode) {
		try {
			cashRegisterManagement.activateCashRegister(cashRegisterCode.getValue());
			return responseGenerator.buildSuccessResponse(Status.OK);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@POST
	@Path("deactivate-cash-register")
	public Response deactivateCashRegister(@Context HttpServletRequest request, WrappedValue<Long> cashRegisterCode) {
		try {
			cashRegisterManagement.deactivateCashRegister(cashRegisterCode.getValue());
			return responseGenerator.buildSuccessResponse(Status.OK);
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}

}
