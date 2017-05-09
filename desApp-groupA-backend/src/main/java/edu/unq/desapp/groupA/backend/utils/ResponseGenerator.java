package edu.unq.desapp.groupA.backend.utils;

import javax.ws.rs.core.Response;

public class ResponseGenerator  {
	
	public Response responseOK(String message){
		Response response = Response.status(Response.Status.OK)
	 			.entity(message)			   
	 			.build();
		return response;
	}
	
	public Response responseBadRequest(String message){
		Response response = Response.status(Response.Status.BAD_REQUEST)
	 			.entity(message)			   
	 			.build();
		return response;
	}
	
}
