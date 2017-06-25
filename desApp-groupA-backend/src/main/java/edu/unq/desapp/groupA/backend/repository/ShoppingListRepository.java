package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.repository.pagination.PageRequest;
import edu.unq.desapp.groupA.backend.repository.pagination.PageRequestBuilder;
import edu.unq.desapp.groupA.backend.repository.pagination.PageResponse;


@Repository
public class ShoppingListRepository extends HibernateGenericDAO<ShoppingList> {

	private static final long serialVersionUID = 7566968066160299788L;

	private List<ShoppingList> shoppingLists;

	public ShoppingListRepository() {
		this.shoppingLists = new ArrayList<ShoppingList>();
	}

	public List<ShoppingList> getShoppingLists() {
		return shoppingLists;
	}

	public void setShoppingLists(List<ShoppingList> shoppingLists) {
		this.shoppingLists = shoppingLists;
	}
	
	public PageResponse<ShoppingList> findPageByUserId(Integer pageNumber, Integer pageSize, Long userId) {
		String query = "FROM ShoppingList sl "
						+ "WHERE sl.user.id = " + userId;
		PageRequest<ShoppingList> pageRequest = new PageRequestBuilder<ShoppingList>(getDomainClass())
				.setPageNumber(pageNumber)
				.setPageSize(pageSize)
				.setQuery(query)
				.build();
		return findByPage(pageRequest);
	}

	@Override
	public Class<ShoppingList> getDomainClass() {
		return ShoppingList.class;
	}
	
}
