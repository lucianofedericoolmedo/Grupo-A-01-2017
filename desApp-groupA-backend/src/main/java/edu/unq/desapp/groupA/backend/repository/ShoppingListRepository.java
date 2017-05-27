package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.ShoppingList;


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

	@Override
	public Class<ShoppingList> getDomainClass() {
		return ShoppingList.class;
	}
	
}
