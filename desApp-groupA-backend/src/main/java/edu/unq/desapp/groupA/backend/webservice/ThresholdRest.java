package edu.unq.desapp.groupA.backend.webservice;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.service.ThresholdService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;


@Service
@Produces("application/json")
@Consumes("application/json")
@Path("/thresholds")
public class ThresholdRest {

	@Autowired
	private ResponseGenerator responseGenerator;
	
	@Autowired
	public ThresholdService thresholdsService;

	@GET
	@Path("/all-possibles")
	public Response fetchAllThresholdsPossibles() {
		try {
			return responseGenerator.buildSuccessResponse(thresholdsService.fetchAllThresholdsPossibles());
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@GET
	@Path("/all-criterias-possibles")
	public Response fetchAllThresholdsCriteriasPossibles() {
		try {
			return responseGenerator.buildSuccessResponse(thresholdsService.fetchAllThresholdsCriteriasPossibles());
		} catch (Exception e) {
			return responseGenerator.buildErrorResponse(e);
		}
	}
	
	@GET
	public Response ok() {
		return responseGenerator.responseOK("OK");
	}
	
}
