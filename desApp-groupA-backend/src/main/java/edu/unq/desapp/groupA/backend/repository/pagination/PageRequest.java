package edu.unq.desapp.groupA.backend.repository.pagination;

import java.util.List;

public class PageRequest<T> {

	// Instance Variables
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Integer totalSize;
	
	private String query;
	
	private String countQuery;
	
	private List<T> result;
	
	// Constructors
	public PageRequest () { }
	
	public PageRequest (Integer pageNumber, Integer pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public PageRequest (Integer pageNumber, Integer pageSize, String query, String countQuery) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.query = query;
		this.countQuery = countQuery;
	}

	// Getters and Setters
	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public String getCountQuery() {
		return countQuery;
	}

	public void setCountQuery(String countQuery) {
		this.countQuery = countQuery;
	}
	
}
