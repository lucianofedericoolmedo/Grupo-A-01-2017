package edu.unq.desapp.groupA.backend.webservice;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import edu.unq.desapp.groupA.backend.model.PersistenceEntity;
import edu.unq.desapp.groupA.backend.service.GenericService;
import edu.unq.desapp.groupA.backend.utils.ResponseGenerator;

public abstract class GenericRest <T extends PersistenceEntity> {

	@Autowired
	private ResponseGenerator responseGenerator;

	public abstract GenericService<T> getService();

	public Response find(Long id) {
		try{
			return responseGenerator.buildSuccessResponse(this.getService().find(id));
		} catch (RuntimeException re) {
			return responseGenerator.buildResponse(re.getMessage(), Status.BAD_REQUEST);
		} catch (Exception e) {
			return responseGenerator.buildResponse(e.getMessage(), Status.BAD_REQUEST);
		}
	}
	
	public Response findAll() {
		try {
			return responseGenerator.buildSuccessResponse(this.getService().findAll());
		} catch (RuntimeException re) {
			return responseGenerator.buildResponse(re.getMessage(), Status.BAD_REQUEST);
		} catch (Exception e) {
			return responseGenerator.buildResponse(e.getMessage(), Status.BAD_REQUEST);
		}
	}

	public Response create(T entity) {
		try {
			return responseGenerator.buildSuccessResponse(this.getService().save(entity));
		} catch (RuntimeException re) {
			return responseGenerator.buildResponse(re.getMessage(), Status.BAD_REQUEST);
		} catch (Exception e) {
			return responseGenerator.buildResponse(e.getMessage(), Status.BAD_REQUEST);
		}
	}
	
	public Response update(T entity) {
		try {
			return responseGenerator.buildSuccessResponse(this.getService().update(entity));
		} catch (RuntimeException re) {
			return responseGenerator.buildResponse(re.getMessage(), Status.BAD_REQUEST);
		} catch (Exception e) {
			return responseGenerator.buildResponse(e.getMessage(), Status.BAD_REQUEST);
		}
	}

	public Response delete(Long id) {
		try {
			this.getService().delete(id);
			return responseGenerator.buildResponse(Status.OK);
		} catch (RuntimeException re) {
			return responseGenerator.buildResponse(re.getMessage(), Status.BAD_REQUEST);
		} catch (Exception e) {
			return responseGenerator.buildResponse(e.getMessage(), Status.BAD_REQUEST);
		}
	}

	public Response findByPage(Integer pageNumber, Integer pageSize) {
		try {
			return responseGenerator.buildSuccessResponse(getService().findByPage(pageNumber, pageSize));
		} catch (RuntimeException re) {
			return responseGenerator.buildResponse(re.getMessage(), Status.BAD_REQUEST);
		} catch (Exception e) {
			return responseGenerator.buildResponse(e.getMessage(), Status.BAD_REQUEST);
		} 
	}

}
