package edu.unq.desapp.groupA.backend.repository.pagination;

import java.util.List;

public class PageResponse<T> {
	
	// Instance Variables
	private List<T> result;
	
	private Integer totalSize;
	
	// Constructors
	public PageResponse () {
		
	}
	
	public PageResponse (List<T> result, Integer totalSize) {
		this.setResult(result);
		this.setTotalSize(totalSize);
	}

	// Getters and Setters
	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

}
