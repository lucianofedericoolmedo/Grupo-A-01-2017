package edu.unq.desapp.groupA.backend.repository;

import java.io.Serializable;
import java.util.List;

import edu.unq.desapp.groupA.backend.repository.pagination.PageRequest;
import edu.unq.desapp.groupA.backend.repository.pagination.PageResponse;

public interface GenericRepository<T> {

	void save(T entity);

	void delete(T entity);

	void update(T entity);

	T findById(Serializable id);

	List<T> findAll();

	void deleteById(Serializable id);

	int count();

	List<T> findByExample(T exampleObject);

	PageResponse<T> findByPage(PageRequest<T> pageRequest);
	
	Class<T> getDomainClass();
	
}
