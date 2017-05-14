package edu.unq.desapp.groupA.backend.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.repository.GenericRepository;

public abstract class GenericService<T> {
	
	public abstract GenericRepository<T> getRepository();
	
	@Transactional
	public T save(T entity) {
		this.getRepository().save(entity);
		System.out.println(entity);
		return entity;
	}
	
	@Transactional
	public T update(T entity) {
		this.getRepository().update(entity);
		return entity;
	}
	
	@Transactional
	public T find(Long id) {
		return this.getRepository().findById(id);
	}
	
	@Transactional
	public void delete(T entity) {
		this.getRepository().delete(entity);
	}
	
	public List<T> findAll() {
		return this.getRepository().findAll();
	}
	
	
	@Transactional
	public void delete(Long id) {
		this.getRepository().deleteById(id);
	}

}
