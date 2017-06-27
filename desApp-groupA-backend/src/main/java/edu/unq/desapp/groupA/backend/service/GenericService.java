package edu.unq.desapp.groupA.backend.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.repository.GenericRepository;
import edu.unq.desapp.groupA.backend.repository.pagination.PageRequest;
import edu.unq.desapp.groupA.backend.repository.pagination.PageRequestBuilder;
import edu.unq.desapp.groupA.backend.repository.pagination.PageResponse;


@Transactional
public abstract class GenericService<T> {
	
	public abstract GenericRepository<T> getRepository();
	
	public T save(T entity) {
		this.getRepository().save(entity);
		return entity;
	}
	
	public T update(T entity) {
		this.getRepository().update(entity);
		return entity;
	}
	
	public T find(Long id) {
		return this.getRepository().findById(id);
	}
	
	public void delete(T entity) {
		this.getRepository().delete(entity);
	}
	
	public List<T> findAll() {
		return this.getRepository().findAll();
	}
	
	public void delete(Long id) {
		this.getRepository().deleteById(id);
	}

	public PageResponse<T> findByPage(Integer pageNumber, Integer pageSize) {
		PageRequest<T> pageRequest = new PageRequestBuilder<T>(getRepository().getDomainClass())
				.setPageSize(pageSize)
				.setPageNumber(pageNumber)
				.build();
		return getRepository().findByPage(pageRequest);
	}

}
