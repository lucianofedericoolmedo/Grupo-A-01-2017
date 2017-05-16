package edu.unq.desapp.groupA.backend.utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Service;


@Service
public class ResponseGenerator  {

	public Response responseOK(String message){
		return buildResponse(message, Status.OK);
	}

	public Response responseBadRequest(String message){
		return buildResponse(message, Status.BAD_REQUEST);
	}
	
	public <T> Response buildSuccessResponse(T entity) {
		return buildResponse(entity, Status.OK);
	}

	public <T> Response buildResponse(Status status) {
		return Response.status(status)
				.build();
	}
	
	public <T> Response buildResponse(T entity, Status status) {
		return Response.status(status)
				.entity(entity)
				.build();
	}

}
