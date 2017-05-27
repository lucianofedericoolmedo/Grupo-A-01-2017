package edu.unq.desapp.groupA.backend.repository.pagination;

public class PageRequestBuilder<T> {

	// Instance Variables
	private Class<T> type;
	
	private PageRequest<T> pageRequest;
	
	private Integer pageSize;
	
	private Integer pageNumber;
	
	private String query;
	
	private String countQuery;

	// Defaults
	private final Integer defaultPageSize = 10;

	private final Integer defaultPageNumber = 0;
	
	private final String defaultQuery;
	
	// Constructors
	public PageRequestBuilder(Class<T> type) {
		this.setType(type);
		this.setPageRequest(new PageRequest<T>());
		defaultQuery = "from " + type.getName();
	}

	// Getters and Setters
	public PageRequest<T> getPageRequest() {
		return pageRequest;
	}

	public PageRequestBuilder<T> setPageRequest(PageRequest<T> pageRequest) {
		this.pageRequest = pageRequest;
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public PageRequestBuilder<T> setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public PageRequestBuilder<T> setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		return this;
	}

	public String getQuery() {
		return query;
	}

	/**
	 * Sets the query to find the entity. This field is optional as it will be set with a default
	 * query without filtering by any value if this method is not used.
	 * If this method is set with a custom query for filtering, then the custom query should start
	 * with 'from ...'. If it not start with 'from ...' then a custom countQuery should be set to
	 * avoid unexpected behaviors in the total count query.
	 * @param query
	 * @return
	 */
	public PageRequestBuilder<T> setQuery(String query) {
		this.query = query;
		return this;
	}

	public String getCountQuery() {
		return countQuery;
	}

	public PageRequestBuilder<T> setCountQuery(String countQuery) {
		this.countQuery = countQuery;
		return this;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	// Logic
	public PageRequest<T> build() {
		this.pageRequest.setPageNumber(pageNumber != null ? pageNumber : defaultPageNumber);
		this.pageRequest.setPageSize(pageSize != null ? pageSize : defaultPageSize);
		this.buildAndSetQueries();
		return pageRequest;
	}

	private void buildAndSetQueries() {
		String buildingQuery = query != null ? query : defaultQuery;
		this.pageRequest.setQuery(buildingQuery);
		
		String buildingCountQuery = countQuery != null ? countQuery : "select count(*) " + buildingQuery; 
		this.pageRequest.setCountQuery(buildingCountQuery);
	}

}
